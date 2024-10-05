package ui;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "updateCart", value = "/update-cart")
public class UpdateCartServlet extends HttpServlet {

    public void init() {

    }

    private EanItemInfo findCartItem(ArrayList<EanItemInfo> cart, String ean) {
        for (EanItemInfo eanItemInfo : cart) {
            if (eanItemInfo.getEan().equals(ean)) {
                return eanItemInfo;
            }
        }
        return null;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        ArrayList<EanItemInfo> cart = (ArrayList<EanItemInfo>) session.getAttribute("cart");
        String ean = request.getParameter("ean");
        int nrOfCopiesCart = Integer.parseInt(request.getParameter("nrOfCopiesCart"));
        int nrOfCopiesStock = Integer.parseInt(request.getParameter("nrOfCopiesStock"));
        EanItemInfo eanItemInfo = findCartItem(cart, ean);
        String url = "/cart";
        if (nrOfCopiesCart > 0){
            if (nrOfCopiesCart > nrOfCopiesStock) {
                url += "?message=out&ean=" + eanItemInfo.getEan();
            }
            eanItemInfo.setNrOfCopies(nrOfCopiesCart);
        }
        else {
            cart.remove(eanItemInfo);
        }
        session.setAttribute("cart", cart);
        response.setContentType("text/html");
        response.getWriter().println("<meta http-equiv=\"Refresh\" content=\"0; URL=" + url + "\">");
    }

    public void destroy() {
    }
}
