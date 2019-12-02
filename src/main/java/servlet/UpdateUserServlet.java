//package servlet;
//
//import crud.service.UserUserServiceImpl;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebServlet("/admin/update")
//public class UpdateUserServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        HttpSession session = req.getSession(false);
//        session.setAttribute("newUser", new UserUserServiceImpl().getById(Long.parseLong(req.getParameter("id"))));
//        resp.sendRedirect("user_modify.jsp");
//    }
//}