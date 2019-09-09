package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/admin/*", "/user", "/jsp/*"})
public class AuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {

            String role = (String) session.getAttribute("role");

            if (role.equals("admin")) {
                filterChain.doFilter(request, response);

            } else if (role.equals("user")) {
                    req.getRequestDispatcher("/user").forward(req, res);

            } else {
                session.setAttribute("info", "you do not have sufficient privileges");
                req.getRequestDispatcher("/start").forward(req, res);
            }
        } else {
            String servletPath = req.getServletPath();

            if (servletPath.equals("/admin/add")) {
                filterChain.doFilter(request, response);

            } else {
                if (!session.getAttribute("info").equals("choose another login")) {
                    session.setAttribute("info", "log in please");
                }
                req.getRequestDispatcher("/start").forward(req, res);
            }
        }
    }
}