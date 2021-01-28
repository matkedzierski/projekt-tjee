package emotki;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.ejb.Stateless;
import javax.servlet.http.Part; 
import javax.xml.ws.BindingProvider;

@Stateless
public class Kontroler {
    private Map<String, String> map;
    private String status;
    private List<String> fileInfo;
    private Emotki port;
    private String fileContent;
    
    public void setMap(Map<String, String> map){
        this.map = map;
    }
    
    public Map<String, String> getMap(){ // id | miniatura 150x150
        if(port==null) {
            try{
                setup();
            } catch (Exception ex){
                status = "Nie udało się nawiązać połączenia z serwerem";
                return null;
            }
        }
        loadMap();
        return map;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public boolean postFile(Part part, String description) throws IOException {
        if(port==null) {
            try{
                setup();
            } catch (Exception ex){
                status = "Nie udało się nawiązać połączenia z serwerem";
                return false;
            }
                
        }
        String fileName = part.getSubmittedFileName();
        if(!fileName.substring(fileName.lastIndexOf(".")+1).equals("png")){
            setStatus("Akceptowany typ plików, to wyłącznie PNG!");
            return false;
        }
        byte[] bytes = new byte[(int)part.getSize()];
        part.getInputStream().read(bytes);
        if(port.postFile(fileName, bytes, description)==0)
            return true;
        else{
            setStatus("Serwer napotkał problem z zapisywaniem pliku!");
            return false;
        }
    }

    public List<String> getFileInfo(String id) { // rozszerzenie, opis, data
        if(port==null) {
            try{
                setup();
            } catch (Exception ex){
                status = "Nie udało się nawiązać połączenia z serwerem";
                return null;
            }
        }
        setFileInfo(port.getFileInfo(id)); 
        return fileInfo;
    }

    public void setFileInfo(List<String> fileInfo) {
        this.fileInfo = fileInfo;
    }
    
    public boolean deleteFile(String id){
        if(port==null) try{
                setup();
            } catch (Exception ex){
                status = "Nie udało się nawiązać połączenia z serwerem";
                return false;
            }
        return port.deleteFile(id);
    }
    
    private void setup(){
        String WSDL = "/Emotki.wsdl";
        
        /*// LOCAL (tak się łączę od siebie z komputera)
        Emotki_Service service = new Emotki_Service(Kontroler.class.getResource(WSDL), new QName(
                "http://tjee.itc.wcy.wat.edu.pl:8080/", "Emotki"));
        port = service.getEmotkiPort();
        BindingProvider bindingProvider = (BindingProvider) port;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://tjee.itc.wcy.wat.edu.pl:8080/Emotki/Emotki");
        
        /**/ //TJEE (tak się łączę z TomEE na chmurze)
        Emotki_Service service = new Emotki_Service(Kontroler.class.getResource(WSDL), new QName(
                "http://172.16.65.2:8080/", "Emotki"));
        port = service.getEmotkiPort();
        BindingProvider bindingProvider = (BindingProvider) port;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://172.16.65.2:8080/Emotki/Emotki");
    }

    public String getFileContent(String id) { // zawartosc full size
        if(port==null){
            try{
                setup();
            } catch (Exception ex){
                status = "Nie udało się nawiązać połączenia z serwerem";
                return null;
            }
        }
        return port.getFileContent(id);
    }

    public String getBigImage(String id) { // image 500x500
        if(port==null){
            try{
                setup();
            } catch (Exception ex){
                status = "Nie udało się nawiązać połączenia z serwerem";
                return null;
            }
        }
        return port.getBigImage(id);
    }
    
    private void loadMap(){
        List<String> ids = port.getIDs();
        List<String> thumbnails = port.getFiles();
        map = IntStream.range(0, thumbnails.size())
            .boxed()
            .collect(Collectors.toMap(i -> ids.get(i), i -> thumbnails.get(i)));
    }
    
    public Emotki getPort(){
        if(port==null){
            String WSDL = "/Emotki.wsdl";
            Emotki_Service service = new Emotki_Service(Kontroler.class.getResource(WSDL), new QName(
                    "http://172.16.65.2:8080/", "Emotki"));
            port = service.getEmotkiPort();
            BindingProvider bindingProvider = (BindingProvider) port;
            bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    "http://172.16.65.2:8080/Emotki/Emotki");
        }
        return this.port;
    }
    
}
