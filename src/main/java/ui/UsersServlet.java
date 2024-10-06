package ui;

import bo.Authority;
import bo.MediaHandler;
import bo.UserHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "users", value = "/users")
public class UsersServlet extends HttpServlet {

    public void init() {

    }

    private void getUsers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<UserInfo> users = UserHandler.getUsers();
        request.setAttribute("users", users);
        UserInfo user = (UserInfo)request.getSession().getAttribute("user");
        if (user == null) {
            response.setContentType("text/html");
            response.getWriter().println("<meta http-equiv=\"Refresh\" content=\"0; URL=sign_in.jsp?return=/users\">");
        }
        else if (user.getAuthority() == Authority.ADMIN){
            request.getRequestDispatcher("users.jsp").forward(request, response);
        }
        else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getUsers(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getUsers(request, response);
    }

    public void destroy() {
    }
}
