package ui;

import bo.Authority;
import bo.OrderHandler;
import bo.UserHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "orders", value = "/orders")
public class OrdersServlet extends HttpServlet {

    public void init() {

    }

    private void getOrders(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<OrderInfo> orders = OrderHandler.getOrders();
        request.setAttribute("orders", orders);
        UserInfo user = (UserInfo)request.getSession().getAttribute("user");
        if (user == null) {
            response.setContentType("text/html");
            response.getWriter().println("<meta http-equiv=\"Refresh\" content=\"0; URL=sign_in.jsp?return=/orders\">");
        }
        else if (user.getAuthority() == Authority.ADMIN){
            request.getRequestDispatcher("orders.jsp").forward(request, response);
        }
        else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getOrders(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getOrders(request, response);
    }

    public void destroy() {
    }
}
