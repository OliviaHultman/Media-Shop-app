package ui;

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

    private OrderItemInfo findCartItem(ArrayList<OrderItemInfo> cart, String ean) {
        for (OrderItemInfo orderItemInfo : cart) {
            if (orderItemInfo.getEan().equals(ean)) {
                return orderItemInfo;
            }
        }
        return null;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        ArrayList<OrderItemInfo> cart = (ArrayList<OrderItemInfo>) session.getAttribute("cart");
        String ean = request.getParameter("ean");
        OrderItemInfo orderItemInfo = findCartItem(cart, ean);
        String url = "/webshop";
        if (orderItemInfo == null) {
            cart.add(new OrderItemInfo(ean));
        }
        else if (orderItemInfo.getNrOfCopies() == Integer.parseInt(request.getParameter("nrOfCopies"))) {
            url += "?message=out&ean=" + orderItemInfo.getEan();
        }
        else {
            orderItemInfo.incrementNrOfCopies();
        }
        session.setAttribute("cart", cart);
        response.setContentType("text/html");
        response.getWriter().println("<meta http-equiv=\"Refresh\" content=\"0; URL=" + url + "\">");
    }

    public void destroy() {
    }
}
