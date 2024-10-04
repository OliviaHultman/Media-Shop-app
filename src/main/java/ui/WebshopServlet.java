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

@WebServlet(name = "webshop", value = "/webshop")
public class WebshopServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    private void getWebshop(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        ArrayList<MediaInfo> mediasInfo = MediaHandler.getMedias();
        request.setAttribute("medias", mediasInfo);
        request.getRequestDispatcher("webshop.jsp").forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new ArrayList<EanItemInfo>());
        }
        getWebshop(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getWebshop(request, response);
    }

    public void destroy() {
    }
}
