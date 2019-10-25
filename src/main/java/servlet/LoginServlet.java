package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final String adminEmail = "admin";
    private final String adminPassword = "password";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userEmail = req.getParameter("email");
        String userPassword = req.getParameter("password");

        if (userEmail.equals(adminEmail) && userPassword.equals(adminPassword)) {
            HttpSession session = req.getSession();
            session.setAttribute("role", "admin");
            session.setMaxInactiveInterval(30 * 60);
            Cookie cookieEmail = new Cookie("email", userEmail);
            cookieEmail.setMaxAge(30 * 60);
            resp.addCookie(cookieEmail);
            // TODO сделать редирект
            resp.sendRedirect("someStuff");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            PrintWriter out = resp.getWriter();
            out.println("LABADABA");
            rd.include(req, resp);
        }
    }
}