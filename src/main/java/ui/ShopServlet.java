package ui;

import bo.MediaHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "shop", value = "/shop")
public class ShopServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new ArrayList<>());
        }
        ArrayList<MediaInfo> medias = MediaHandler.getMedias();
        request.setAttribute("medias", medias);
        request.getRequestDispatcher("shop.jsp").forward(request, response);
    }

    public void destroy() {
    }
}
