package ui;

import bo.MediaHandler;
import bo.Type;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "editProduct", value = "edit-product")
public class EditProductServlet extends HttpServlet {
    public void init() {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        MediaHandler.editMedia(new MediaInfo(request.getParameter("ean"),
                request.getParameter("name"), request.getParameter("artist"),
                Type.valueOf(request.getParameter("type")), request.getParameter("label"),
                request.getParameter("genre"), Integer.valueOf(request.getParameter("price")),
                Integer.valueOf(request.getParameter("nrOfCopies"))));
        response.setContentType("text/html");
        response.getWriter().println("<meta http-equiv=\"Refresh\" content=\"0; URL=/products\">");
    }

    public void destroy() {
    }
}
