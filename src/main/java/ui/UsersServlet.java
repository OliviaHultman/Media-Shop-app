package ui;

import bo.Role;
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo)session.getAttribute("user");
        String action = request.getParameter("action");
        if (action == null) {
            if (user == null) {
                response.sendRedirect("sign_in.jsp?return=/users");
            }
            else if (user.getRole() != Role.ADMIN) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
            else {
                ArrayList<UserInfo> users = UserHandler.getUsers();
                request.setAttribute("users", users);
                request.getRequestDispatcher("users.jsp").forward(request, response);
            }
        }
        else {
            switch (action) {
                case "create":
                    Role role;
                    if (user != null && user.getRole() == Role.ADMIN) {
                        role = Role.valueOf(request.getParameter("role"));
                    } else {
                        role = Role.CUSTOMER;
                    }
                    user = new UserInfo(request.getParameter("email"), request.getParameter("firstName"),
                            request.getParameter("lastName"), request.getParameter("password"), role);
                    boolean succeded = UserHandler.createUser(user);
                    String returnUrl = request.getParameter("return");
                    if (succeded) {
                        if (!returnUrl.equals("users")) {
                            session.setAttribute("user", user);
                        }
                    } else {
                        returnUrl = "create_user.jsp?return=" + returnUrl + "&message=duplicate";
                    }
                    response.sendRedirect(returnUrl);
                    break;
                case "update":
                    user = new UserInfo(request.getParameter("email"), request.getParameter("firstName"),
                            request.getParameter("lastName"), request.getParameter("password"),
                            Role.valueOf(request.getParameter("role")));
                    UserHandler.updateUser(user);
                    returnUrl = request.getParameter("return");
                    if (returnUrl.equals("profile.jsp")) {
                        session.setAttribute("user", user);
                    }
                    response.sendRedirect(returnUrl);
                    break;
                case "delete":
                    UserHandler.deleteUser(request.getParameter("email"));
                    response.sendRedirect("users");
                    break;
                case "sign-in":
                    user = UserHandler.getUser(request.getParameter("email"), request.getParameter("password"));
                    returnUrl = request.getParameter("return");
                    if (user != null) {
                        session.setAttribute("user", user);
                    } else {
                        returnUrl = "sign_in.jsp?return=" + returnUrl + "&message=wrong";
                    }
                    response.sendRedirect(returnUrl);
                    break;
                case "sign-out":
                    session.setAttribute("user", null);
                    response.sendRedirect("shop");
                    break;
                default:
            }
        }
    }

    public void destroy() {
    }
}
