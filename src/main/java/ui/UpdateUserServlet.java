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
    @WebServlet(name = "updateUser", value = "/update-user")
    public class UpdateUserServlet extends HttpServlet {

        public void init() {

        }

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            UserInfo user = new UserInfo(request.getParameter("email"), request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("password"), Authority.valueOf(request.getParameter("authority")));
            System.out.println(UserHandler.changeUser(user));
            response.setContentType("text/html");
            String returnUrl = request.getParameter("return");
            if (returnUrl.equals("/profile.jsp")) {
                request.getSession().setAttribute("user", user);
            }
            response.getWriter().println("<meta http-equiv=\"Refresh\" content=\"0; URL=" + returnUrl + "\">");
        }

        public void destroy() {
        }
    }
