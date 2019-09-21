package filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@Component
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/admin/*", "/user", "/jsp/*"})
public class AuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {

            filterChain.doFilter(request, response);

        } else {
            String servletPath = req.getServletPath();
            if (servletPath.equals("/admin/add")) {
                filterChain.doFilter(request, response);
            } else {
                session.getAttribute("info").equals("log in please");
                req.getRequestDispatcher("/").forward(req, res);
            }
        }
    }
}