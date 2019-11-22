package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = UserService.getInstance();
        String loginEmail = req.getParameter("email");

        if (userService.validate(loginEmail, req.getParameter("password"))) {

            User user = null;

            try {
                user = UserService.getInstance().getByEmail(loginEmail);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            HttpSession session = req.getSession();

            session.setAttribute("user", user);

            // setting session to expiry in 10 mins
            session.setMaxInactiveInterval(10 * 60);

            if (user.getRole().equals("admin")) {
                resp.sendRedirect("/admin");
            } else {
                resp.sendRedirect("/user");
            }
        } else {
            req.setAttribute("message", "Either e-mail or password is wrong.");
            req.getRequestDispatcher("/index.jsp").include(req, resp);
        }
    }
}