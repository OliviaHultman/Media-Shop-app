package ui;

import bo.MediaHandler;
import bo.Order;
import bo.OrderHandler;
import bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "checkout", value = "/checkout")
public class CheckoutServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        OrderHandler.createOrder((ArrayList<EanItemInfo>) session.getAttribute("cart"), (UserInfo) session.getAttribute("user"));
        request.getRequestDispatcher("confirmation.jsp").forward(request, response);
    }

    public void destroy() {
    }
}