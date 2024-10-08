package ui;

import bo.Authority;
import bo.MediaHandler;
import bo.Type;
import bo.UserHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "products", value = "/products")
public class ProductsServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        String action = request.getParameter("action");
        switch (action) {
            case "init":
                request.setAttribute("genres",MediaHandler.getGenres());
                request.getRequestDispatcher(request.getParameter("return")).forward(request, response);
            case "add":
                if (request.getParameter("ean").length() != 13) {
                    response.sendRedirect("products?action=init&message=format");
                }
                boolean succeded = MediaHandler.addMedia(new MediaInfo(request.getParameter("ean"),
                        request.getParameter("name"), request.getParameter("artist"),
                        Type.valueOf(request.getParameter("type")), request.getParameter("label"),
                        request.getParameter("genre"), Integer.parseInt(request.getParameter("price")),
                        Integer.parseInt(request.getParameter("nrOfCopies"))));
                String returnUrl = "products";
                if (!succeded) {
                    returnUrl += "?action=init&message=duplicate";
                }
                response.sendRedirect(returnUrl);
                break;
            case "update":
                MediaHandler.editMedia(new MediaInfo(request.getParameter("ean"),
                        request.getParameter("name"), request.getParameter("artist"),
                        Type.valueOf(request.getParameter("type")), request.getParameter("label"),
                        request.getParameter("genre"), Integer.valueOf(request.getParameter("price")),
                        Integer.valueOf(request.getParameter("nrOfCopies"))));
                response.sendRedirect("products");
                break;
            case "delete":
                MediaHandler.deleteMedia(request.getParameter("ean"));
                response.sendRedirect("products");
                break;
            case "add-genre":
                MediaHandler.addGenre(request.getParameter("genre"));
                response.sendRedirect("products");
                break;
            case "delete-genre":
                MediaHandler.deleteGenre(request.getParameter("genre"));
                response.sendRedirect("products");
                break;
            case "get-genres":
                break;
            default:
                ArrayList<MediaInfo> products = MediaHandler.getMedias();
                ArrayList<String> genres = MediaHandler.getGenres();
                request.setAttribute("products", products);
                request.setAttribute("genres", genres);
                if (user == null) {
                    response.sendRedirect("sign_in.jsp?return=/products");
                }
                else if (user.getAuthority() == Authority.ADMIN){
                    request.getRequestDispatcher("products.jsp").forward(request, response);
                }
                else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
        }
    }

    public void destroy() {
    }
}
