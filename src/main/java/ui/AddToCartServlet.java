package ui;

import bo.UserHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "addToCart", value = "/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    public void init() {

    }

    private CartItemInfo findCartItem(ArrayList<CartItemInfo> cart, String ean) {
        for (CartItemInfo cartItemInfo : cart) {
            if (cartItemInfo.getEan().equals(ean)) {
                return cartItemInfo;
            }
        }
        return null;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        ArrayList<CartItemInfo> cart = (ArrayList<CartItemInfo>) session.getAttribute("cart");
        String ean = request.getParameter("ean");
        CartItemInfo cartItemInfo = findCartItem(cart, ean);
        if (cartItemInfo == null) {
            cart.add(new CartItemInfo(ean));
        }
        else {
            cartItemInfo.incrementNrOfCopies();
        }
        session.setAttribute("cart", cart);
        response.setContentType("text/html");
        response.getWriter().println("<meta http-equiv=\"Refresh\" content=\"0; URL=/webshop\">");
    }

    public void destroy() {
    }
}
