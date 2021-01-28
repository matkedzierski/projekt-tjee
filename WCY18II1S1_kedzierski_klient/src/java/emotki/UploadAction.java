package emotki;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2 MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class UploadAction extends HttpServlet {

    @EJB
    private Kontroler kontroler;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                
        //plik i opis
        Part part = request.getPart("file");
        String description = request.getParameter("desc");
        
        //prześlij i w zależności od wyniku przekieruj dalej
        
            if(kontroler.getPort()==null || !kontroler.postFile(part, description)){
                kontroler.setStatus("Nie udało się ustanowić połączenia z serwerem");
                getServletContext().getRequestDispatcher("/error.jsp").forward(
                request, response);
            } else{
            getServletContext().getRequestDispatcher("/galeria.jsp").forward(
                request, response);
            }
            
        }
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
