package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/update")
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // TODO setParameter без сессии
        HttpSession session = req.getSession(false);
        session.setAttribute("user", UserService.getInstance().get(Long.parseLong(req.getParameter("id"))));
        resp.sendRedirect(req.getContextPath() + "/user_modify.jsp");
    }
}