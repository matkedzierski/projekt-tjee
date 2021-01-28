package emotki;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.*;
import com.mongodb.MongoClient;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.model.Updates;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

/**
 * klasa implementująca usługę webową oraz realizująca dostęp do bazy danych
 * @author Mefju
 */
@WebService(serviceName = "Emotki")
@Stateless()
@Singleton
public class Emotki {

    //private static final String CONN = "mongodb://localhost:27017"; //lokalna db -> lokalna db
    //private static final String CONN = "mongodb://10.6.79.42:27017"; //chmura -> lokalna db
    //private static final String CONN = "mongodb://student:student-TJEE@tjee.itc.wcy.wat.edu.pl:27017/studenci_2020"; //lokalny serwer -> chmura db
    private static final String CONN = "mongodb://student:student-TJEE@172.16.65.2:27017/studenci_2020"; //chmura -> chmura db
    MongoCollection<Emotikona> col = null;
    MongoClient mongo;
    MongoDatabase db;
    CodecRegistry kodek;
    
    /**
     * pobiera informacje na temat obrazu
     * @param id identyfikator obrazu
     * @return rozszerzenie pliku, opis oraz data dodania jako tablica Stringów
     */
    @WebMethod(operationName = "getFileInfo")
    public String[] getFileInfo(@WebParam(name = "id") String id) { //ext, desc, date
        if(col==null) init();
        Emotikona ret = col.find(and(eq("_id", new ObjectId(id)), eq("deleted", false))).first();
        if(ret == null || ret.getContent().isEmpty())
            return null;
        else
            return new String[] {ret.getExt(), ret.getDesc(), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(ret.getAdded())};
    }
    
    /**
     * pobiera cala zawartosc obrazu w oryginalnym rozmiarze.
     * @param id identyfikator obrazu do pobrania
     * @return zakodowany w base64 obraz 
     */
    @WebMethod(operationName = "getFileContent")
    public String getFileContent(@WebParam(name = "id") String id) { //zawartość full size
        if(col==null) init();
        Emotikona ret = col.find(and(eq("_id", new ObjectId(id)), eq("deleted", false))).first();
        if(ret == null || ret.getContent().isEmpty())
            return null;
        else
            return ret.getContent();
    }
    
    /**
     * pobiera obraz w rozmiarze max 400x400 przeskalowany proporcjonalnie
     * @param id identyfikator obrazu do pobrania
     * @return zakodowany w base64 obraz 400x400
     */
    @WebMethod(operationName = "getBigImage")
    public String getBigImage(@WebParam(name = "id") String id) { //zawartość 400x400
        if(col==null) init();
        Emotikona ret = col.find(and(eq("_id", new ObjectId(id)), eq("deleted", false))).first();
        if(ret!=null)
            return ret.getBigsize();
        else
            return null;
    }

    /**
     * imituje usuwanie z bazy. Ustawia flage deleted na true i zawartosc, miniature oraz duzy format 
     * ustawia na null. 
     * @param id identyfikator do usuniecia
     * @return sukces/porazka
     */
    @WebMethod(operationName = "deleteFile")
    public boolean deleteFile(@WebParam(name = "id") String id) {
        if(col==null) init();
        col.updateOne(eq("_id", new ObjectId(id)), Updates.combine(
            Updates.set("deleted", true),
            Updates.set("content", null),
            Updates.set("bigsize", null),
            Updates.set("thumbnail", null)));
        return true;
    }

    /**
     * pobiera miniatury nieusunietych obrazow, od najstarszych
     * @return lista miniatur zakodowanych w base64
     */
    @WebMethod(operationName = "getFiles")
    public List<String> getFiles() { // same miniatury 150x150
        if(col==null) init();
        FindIterable<Emotikona> iter = col.find(eq("deleted", false)).sort(new BasicDBObject("added", 1));
        List<String> thumbnails = new ArrayList();
        for (Emotikona tmp : iter) 
            thumbnails.add(tmp.getThumbnail());
        return thumbnails;
    }
    
    /**
     * pobiera identyfikatory nieusunietych obrazow sortowane rosnaco wedlug daty (od najstarszych)
     * @return identyfikatory obrazow
     */
    @WebMethod(operationName = "getIDs")
    public List<String> getIDs() {
        if(col==null) init();
        FindIterable<Emotikona> iter = col.find(eq("deleted", false)).sort(new BasicDBObject("added", 1));
        List<String> ids = new ArrayList();
        for (Emotikona tmp : iter) 
            ids.add(tmp.getId().toString());
        return ids;
    }
    
    /**
     * dodaje plik do bazy danych. Na poczatku dodaje tylko opis, nazwe, rozszerzenie i miniature
     * w celu przyspieszenia procesu uploadu. Nastepnie w osobnym watku dopisuje do bazy zawartosc
     * oraz duzy rozmiar pliku. W ten sposob skraca sie czas odpowiedzi uslugi webowej.
     * @param filename - oryginalna nazwa pliku (tylko do identyfikacji bezposrednio w bazie)
     * @param content - zawartosc oryginalnego obrazu zakodowana w base64
     * @param desc - krotki opis uzytkownika
     * @return 0 jesli sie uda, -1 jesli nie. Integer w celu mozliwosci rozszerzenia o kody bledow.
     */
    @WebMethod(operationName = "postFile")
    public int postFile(@WebParam(name = "filename") String filename, @WebParam(name = "content") byte[] content, @WebParam(name = "desc") String desc) {
        if(col==null) init();
        String ext = filename.substring(filename.lastIndexOf('.'));
        String cont = Base64.getEncoder().encodeToString(content);
        String thumb;
        try{
            thumb = scale(cont, ext, 150);
        } catch(IOException e){
            return -1;
        }
        Emotikona em = new Emotikona(desc, filename, ext, null, thumb , null); //na razie tylko id i miniatura
        col.insertOne(em);
        new Thread(()->{       // czasochłonne, ale nie musi być od razu
            String big="";
            try{
                big = scale(cont, ext, 400);
            } catch(IOException e){}
            col.updateOne(eq("_id",em.getId()), 
                    Updates.combine(
                        Updates.set("bigsize", big),
                        Updates.set("content", cont)));
        }).start();
        return 0;
    }    
    
    /**
     * nawiazuje polaczenie z baza danych
     */
    private void init(){
        //mapowanie na POJO
        kodek = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        
        //nawiazanie polaczenia
        mongo = new MongoClient(new MongoClientURI(CONN));
        db = mongo.getDatabase("studenci_2020");
        col = db.getCollection("kedzierski", Emotikona.class).withCodecRegistry(kodek);
    }
    
    /**
     * sluzy do skalowania obrazu zakodowanego w base65
     * @param in - wejscie (Base64)
     * @param ext - rozszerzenie (np. .png)
     * @param max_size - maksymalny rozmiar - wysokosc/szerokosc
     * @return - przeskalowany obraz (Base64)
     * @throws IOException 
     */
    private String scale(String in, String ext, int max_size) throws IOException {
        // Base64 -> BufferedImage
        byte[] imageByte = Base64.getDecoder().decode(in);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        BufferedImage original = ImageIO.read(bis);
        bis.close();
        
        // ustalenie wymiarów
        int wIn = original.getWidth();
        int hIn = original.getHeight();
        int wOut, hOut;
        if(wIn > hIn){ //szeroki img
            wOut = max_size; 
            hOut = max_size*hIn/wIn; // proporcjonalnie
        } else { // wysoki img
            hOut = max_size; 
            wOut = max_size*wIn/hIn; // proporcjonalnie
        }
        
        // skalowanie
        BufferedImage scaled = new BufferedImage(wOut, hOut, BufferedImage.TRANSLUCENT);
        Graphics2D g2 = scaled.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(original, 0, 0, wOut, hOut, null);
        g2.dispose();
        
        // BufferedImage -> Base64
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(scaled, ext.substring(1), bos); //rozszerzenie bez kropki
        byte[] outputBytes = bos.toByteArray();
        String output = Base64.getEncoder().encodeToString(outputBytes);
        return output;
    }
    
    
    
}