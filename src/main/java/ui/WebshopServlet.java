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
            session.setAttribute("cart", new ArrayList<CartItem>());
        }
        getWebshop(request, response);
    }

    private CartItem findCartItem(ArrayList<CartItem> cart, String ean) {
        for (CartItem cartItem : cart) {
            if (cartItem.getEan().equals(ean)) {
                return cartItem;
            }
        }
        return null;
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        ArrayList<CartItem> cart = (ArrayList<CartItem>) session.getAttribute("cart");
        String ean = request.getParameter("ean");
        CartItem cartItem = findCartItem(cart, ean);
        if (cartItem == null) {
            cart.add(new CartItem(ean));
        }
        else {
            cartItem.incrementNrOfCopies();
        }
        session.setAttribute("cart", cart);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String command = request.getParameter("command");
        if (command != null) {
            switch (command) {
                case "addToCart":
                    addToCart(request, response, session);
                    break;
                default:
            }
        }
        getWebshop(request, response);
    }

    public void destroy() {
    }
}
