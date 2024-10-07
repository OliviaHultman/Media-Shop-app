package ui;

import bo.MediaHandler;
import bo.UserHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "deleteUser", value = "delete-user")
public class DeleteUserServlet extends HttpServlet {
    public void init() {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserHandler.deleteUser(request.getParameter("email"));
        response.setContentType("text/html");
        response.getWriter().println("<meta http-equiv=\"Refresh\" content=\"0; URL=/users\">");
    }

    public void destroy() {
    }
}
