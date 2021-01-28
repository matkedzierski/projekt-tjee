package emotki;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAction extends HttpServlet {

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
        
        String id = request.getParameter("image");
        if(kontroler.deleteFile(id))
            getServletContext().getRequestDispatcher("/galeria.jsp").forward(
                request, response);
        else{
            getServletContext().getRequestDispatcher("/display.jsp?image="+id).forward(
                request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }


    
}
