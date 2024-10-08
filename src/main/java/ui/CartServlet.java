package ui;

import bo.MediaHandler;
import bo.OrderHandler;
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

    private OrderItemInfo findCartItem(ArrayList<OrderItemInfo> cart, String ean) {
        for (OrderItemInfo orderItem : cart) {
            if (orderItem.getEan().equals(ean)) {
                return orderItem;
            }
        }
        return null;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        ArrayList<OrderItemInfo> cart = (ArrayList<OrderItemInfo>) session.getAttribute("cart");
        String action = request.getParameter("action");
        if (action == null) {
            ArrayList<MediaItemInfo> order = MediaHandler.getCartMedias(cart);
            request.setAttribute("order", order);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
        switch (action) {
            case "add":
                String ean = request.getParameter("ean");
                OrderItemInfo item = findCartItem(cart, ean);
                String returnUrl = "shop";
                if (item == null) {
                    cart.add(new OrderItemInfo(ean));
                }
                else if (item.getNrOfCopies() == Integer.parseInt(request.getParameter("nrOfCopies"))) {
                    returnUrl += "?message=out&ean=" + item.getEan();
                }
                else {
                    item.incrementNrOfCopies();
                }
                session.setAttribute("cart", cart);
                response.sendRedirect(returnUrl);
                break;
            case "update":
                ean = request.getParameter("ean");
                int nrOfCopiesCart = Integer.parseInt(request.getParameter("nrOfCopiesCart"));
                int nrOfCopiesStock = Integer.parseInt(request.getParameter("nrOfCopiesStock"));
                item = findCartItem(cart, ean);
                returnUrl = "cart";
                if (nrOfCopiesCart > 0) {
                    if (nrOfCopiesCart > nrOfCopiesStock) {
                        returnUrl += "?message=out&ean=" + item.getEan();
                    }
                    item.setNrOfCopies(nrOfCopiesCart);
                }
                else {
                    cart.remove(item);
                }
                session.setAttribute("cart", cart);
                response.sendRedirect(returnUrl);
                break;

        }
    }

    public void destroy() {
    }
}
