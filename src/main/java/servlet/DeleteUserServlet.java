//package servlet;
//
//import crud.model.User;
//import crud.service.UserUserServiceImpl;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/admin/delete")
//public class DeleteUserServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        UserUserServiceImpl userServiceImpl = new UserUserServiceImpl();
//
//        String idString = req.getParameter("id");
//        String email = req.getParameter("email");
//        String password = req.getParameter("password");
//        String name = req.getParameter("name");
//        Integer age = Integer.valueOf(req.getParameter("age"));
//        String role = req.getParameter("role");
//
//        userServiceImpl.delete(new User(email, password, name, age, role));
//        resp.sendRedirect("/admin");
//    }
//}