package ui;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "updateCart", value = "/update-cart")
public class UpdateCartServlet extends HttpServlet {

    public void init() {

    }

    private OrderItemInfo findCartItem(ArrayList<OrderItemInfo> cart, String ean) {
        for (OrderItemInfo orderItem : cart) {
            if (orderItem.getEan().equals(ean)) {
                return orderItem;
            }
        }
        return null;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        ArrayList<OrderItemInfo> cart = (ArrayList<OrderItemInfo>) session.getAttribute("cart");
        String ean = request.getParameter("ean");
        int nrOfCopiesCart = Integer.parseInt(request.getParameter("nrOfCopiesCart"));
        int nrOfCopiesStock = Integer.parseInt(request.getParameter("nrOfCopiesStock"));
        OrderItemInfo orderItem = findCartItem(cart, ean);
        String url = "/cart";
        if (nrOfCopiesCart > 0){
            if (nrOfCopiesCart > nrOfCopiesStock) {
                url += "?message=out&ean=" + orderItem.getEan();
            }
            orderItem.setNrOfCopies(nrOfCopiesCart);
        }
        else {
            cart.remove(orderItem);
        }
        session.setAttribute("cart", cart);
        response.setContentType("text/html");
        response.getWriter().println("<meta http-equiv=\"Refresh\" content=\"0; URL=" + url + "\">");
    }

    public void destroy() {
    }
}
