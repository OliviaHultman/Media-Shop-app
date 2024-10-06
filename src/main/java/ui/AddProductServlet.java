package ui;

import bo.MediaHandler;
import bo.Type;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

    @WebServlet(name = "addProduct", value = "/add-product")
    public class AddProductServlet extends HttpServlet {

        public void init() {

        }

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            response.setContentType("text/html");
            if (request.getParameter("ean").length() != 13) {
                response.sendRedirect("get-genres?message=format");
            }
            boolean succeded = MediaHandler.addMedia(new MediaInfo(request.getParameter("ean"),
                    request.getParameter("name"), request.getParameter("artist"),
                    Type.valueOf(request.getParameter("type")), request.getParameter("label"),
                    request.getParameter("genre"), Integer.parseInt(request.getParameter("price")),
                    Integer.parseInt(request.getParameter("nrOfCopies"))));
            String returnUrl;
            if (!succeded) {
                returnUrl = "/get-genres?message=duplicate";
            }
            else {
                returnUrl = "/products";
            }
            response.getWriter().println("<meta http-equiv=\"Refresh\" content=\"0; URL=" + returnUrl + "\">");
        }

        public void destroy() {
        }
    }
