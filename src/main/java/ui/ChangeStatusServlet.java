package ui;

import bo.Authority;
import bo.OrderHandler;
import bo.Status;
import bo.UserHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "changeStatus", value = "/change-status")
public class ChangeStatusServlet extends HttpServlet {

    public void init() {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        OrderHandler.changeStatus(new OrderInfo(Integer.parseInt(request.getParameter("orderNr")), Status.valueOf(request.getParameter("status"))));
        response.setContentType("text/html");
        response.getWriter().println("<meta http-equiv=\"Refresh\" content=\"0; URL=/orders\">");
    }

    public void destroy() {
    }
}
