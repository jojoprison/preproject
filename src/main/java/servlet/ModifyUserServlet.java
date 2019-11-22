package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/modify")
public class ModifyUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession(false).removeAttribute("newUser");
        resp.sendRedirect("user_modify.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idString = req.getParameter("id");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        Integer age = Integer.valueOf(req.getParameter("age"));
        String role = req.getParameter("role");

        User user = new User(email, password, name, age, role);
        UserService userService = UserService.getInstance();

        if (idString != null) {
            user.setId(Long.valueOf(idString));
            userService.update(user);
        } else {
            userService.add(user);
        }

        req.getSession(false).removeAttribute("newUser");
        resp.sendRedirect("/admin");
    }
}