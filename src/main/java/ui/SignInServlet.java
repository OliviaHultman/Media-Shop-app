package ui;

import bo.UserHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "signIn", value = "/sign-in")
public class SignInServlet extends HttpServlet {

    public void init() {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserInfo userInfo = UserHandler.getUser(request.getParameter("email"), request.getParameter("password"));
        HttpSession session = request.getSession();
        String returnUrl = request.getParameter("return");
        if (userInfo != null) {
            session.setAttribute("user", userInfo);
        }
        else {
            returnUrl = "/sign_in.jsp?return=" + returnUrl + "&message=wrong";
        }
        response.setContentType("text/html");
        response.getWriter().println("<meta http-equiv=\"Refresh\" content=\"0; URL=" + returnUrl + "\">");
    }

    public void destroy() {
    }
}
