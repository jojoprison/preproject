//package servlet;
//
//import crud.model.User;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.IOException;
//
//@WebServlet("/logout")
//public class LogoutServlet extends HttpServlet {
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        response.setContentType("text/html");
//
//        // invalidate the session if exists
//        HttpSession session = request.getSession(false);
//        User user = (User) session.getAttribute("user");
//        System.out.println("User {" + user.getEmail() + ", " + user.getRole() + "} logout.");
//
//        session.invalidate();
//
//        response.sendRedirect("/index.jsp");
//    }
//}