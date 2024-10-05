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

@WebServlet(name = "cart", value = "/cart")
public class CartServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        ArrayList<EanItemInfo> cart = (ArrayList<EanItemInfo>) session.getAttribute("cart");
        ArrayList<OrderItemInfo> order = MediaHandler.getCartMedias(cart);
        request.setAttribute("order", order);
        String url = "cart.jsp";
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    public void destroy() {
    }
}
