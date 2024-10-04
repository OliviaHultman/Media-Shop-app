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
        boolean succeded = UserHandler.createUser(user);
        String returnUrl = request.getParameter("return");
        if (succeded == true) {
            request.getSession().setAttribute("user", user);
        }
        else {
            returnUrl = "/sign_up.jsp?return=" + returnUrl + "&message=duplicate";
        }
        response.setContentType("text/html");
        response.getWriter().println("<meta http-equiv=\"Refresh\" content=\"0; URL=" + returnUrl + "\">");
    }

    public void destroy() {
    }
}
