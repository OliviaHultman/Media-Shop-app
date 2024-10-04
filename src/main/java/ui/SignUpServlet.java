package ui;

import bo.Authority;
import bo.UserHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "signUp", value = "/sign-up")
public class SignUpServlet extends HttpServlet {

    public void init() {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserInfo user = new UserInfo(request.getParameter("email"), request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("password"), Authority.CUSTOMER);
        UserHandler.createUser(user);
        request.getSession().setAttribute("user", user);
        response.setContentType("text/html");
        response.getWriter().println("<meta http-equiv=\"Refresh\" content=\"0; URL=" + request.getParameter("return") + "\">");
    }

    public void destroy() {
    }
}
