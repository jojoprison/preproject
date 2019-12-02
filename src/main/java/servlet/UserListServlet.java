//package servlet;
//
//import crud.service.UserServiceImpl;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/admin")
//public class UserListServlet extends HttpServlet {
//
//    protected void doGet(HttpServletRequest req,
//                         HttpServletResponse resp) throws ServletException, IOException {
//
//        req.setAttribute("users", new UserServiceImpl().getAllUsers());
//        req.getRequestDispatcher("admin/users.jsp").forward(req, resp);
//    }
//}