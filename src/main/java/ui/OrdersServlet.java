package ui;

import bo.Authority;
import bo.OrderHandler;
import bo.Status;
import bo.UserHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "orders", value = "/orders")
public class OrdersServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        ArrayList<OrderItemInfo> cart = (ArrayList<OrderItemInfo>) session.getAttribute("cart");
        String action = request.getParameter("action");
        switch (action) {
            case "create":
                boolean succeded = OrderHandler.createOrder(cart, user.getEmail());
                String returnUrl;
                if (succeded) {
                    session.setAttribute("cart", new ArrayList<>());
                    returnUrl = "confirmation.jsp";
                }
                else {
                    returnUrl = "cart";
                }
                response.sendRedirect(returnUrl);
                break;
            case "update":
                OrderHandler.changeStatus(new OrderInfo(Integer.parseInt(request.getParameter("orderNr")),
                        Status.valueOf(request.getParameter("status"))));
                response.sendRedirect("orders");
                break;
            default:
                ArrayList<OrderInfo> orders = OrderHandler.getOrders();
                request.setAttribute("orders", orders);
                if (user == null) {
                    response.sendRedirect("sign_in.jsp?return=orders");
                }
                else if (user.getAuthority() == Authority.ADMIN){
                    request.getRequestDispatcher("orders.jsp").forward(request, response);
                }
                else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
        }
    }

    public void destroy() {
    }
}
