package ui;

import bo.UserHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {

    public void init() {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserInfo userInfo = UserHandler.getUser(request.getParameter("email"), request.getParameter("password"));
        HttpSession session = request.getSession();
        if (userInfo != null) {
            session.setAttribute("user", userInfo);
        }
        request.getRequestDispatcher("/webshop").forward(request, response);
    }

    public void destroy() {
    }
}
