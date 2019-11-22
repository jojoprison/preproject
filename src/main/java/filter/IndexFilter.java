package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class IndexFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        HttpSession session = req.getSession(false);

        if (session == null && !(uri.endsWith("index.jsp") || uri.endsWith("login"))) {
            res.sendRedirect("/index.jsp");
        } else {
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}