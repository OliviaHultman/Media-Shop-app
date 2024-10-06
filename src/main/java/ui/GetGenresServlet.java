package ui;

import bo.MediaHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "getGenres", value = "/get-genres")
public class GetGenresServlet extends HttpServlet {
    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("genres",MediaHandler.getGenres());
        request.getRequestDispatcher("add_product.jsp").forward(request, response);
    }

    public void destroy() {
    }
}
