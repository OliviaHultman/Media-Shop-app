package ui;

import bo.MediaHandler;
import bo.UserHandler;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "productServlet", value = "/")
public class ProductServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<MediaInfo> mediasInfo = MediaHandler.getMedias();
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("Hello");
    }

    public void destroy() {
    }
}
