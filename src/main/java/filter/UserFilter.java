package filter;

import crud.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/user/*", "/user"})
public class UserFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        User user = (User) req.getSession(false).getAttribute("userAuth");

        System.out.println("USER: " + user);

        if (user == null || !(user.getRole().equals("user") || user.getRole().equals("admin"))) {
            res.sendRedirect("/error");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
