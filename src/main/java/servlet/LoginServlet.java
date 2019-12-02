//package servlet;
//
//import crud.model.User;
//import crud.service.UserServiceImpl;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebServlet("/login")
//public class LoginServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        req.getRequestDispatcher("/index.jsp").forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        UserServiceImpl userServiceImpl = new UserServiceImpl();
//        String loginEmail = req.getParameter("email");
//
//        if (userServiceImpl.validate(loginEmail, req.getParameter("password"))) {
//
//            User user = null;
//
//            user = userServiceImpl.getByEmail(loginEmail);
//
//            HttpSession session = req.getSession();
//
//            session.setAttribute("user", user);
//
//            // setting session to expiry in 10 mins
//            session.setMaxInactiveInterval(10 * 60);
//
//            if (user.getRole().equals("admin")) {
//                resp.sendRedirect("/admin");
//            } else {
//                resp.sendRedirect("/user");
//            }
//        } else {
//            req.setAttribute("message", "Either e-mail or password is wrong.");
//            req.getRequestDispatcher("/index.jsp").include(req, resp);
//        }
//    }
//}