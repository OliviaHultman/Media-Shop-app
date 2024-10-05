package ui;

import bo.Authority;
import bo.User;
import bo.UserHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static bo.Authority.ADMIN;

@WebServlet(name = "createUser", value = "/create-user")
public class CreateUserServlet extends HttpServlet {

    public void init() {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Authority authority;
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        if (user != null && user.getAuthority() == Authority.ADMIN) {
            authority = Authority.valueOf(request.getParameter("authority"));
        }
        else {
            authority = Authority.CUSTOMER;
        }
        UserInfo newUser = new UserInfo(request.getParameter("email"), request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("password"), authority);
        boolean succeded = UserHandler.createUser(newUser);
        String returnUrl = request.getParameter("return");
        if (succeded) {
            if (user == null) {
                session.setAttribute("user", newUser);
            }
        }
        else {
            returnUrl = "/create_user.jsp?return=" + returnUrl + "&message=duplicate";
        }
        response.setContentType("text/html");
        response.getWriter().println("<meta http-equiv=\"Refresh\" content=\"0; URL=" + returnUrl + "\">");
    }

    public void destroy() {
    }
}
