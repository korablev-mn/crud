package servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "RoleUserFilter", urlPatterns = {"/admin/*"})
public class RoleFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        String role = (String) session.getAttribute("role");

        if (role != null) {

            if (role.equals("admin")) {
                filterChain.doFilter(request, response);
            } else if (role.equals("user")) {
                req.getRequestDispatcher("/user").forward(req, res);
            } else {
                session.setAttribute("info", "log in please");
                req.getRequestDispatcher("/start").forward(req, res);
            }
        } else {
            String servletPath = req.getServletPath();
            if (servletPath.equals("/admin/add")) {
                filterChain.doFilter(request, response);
            } else {
                session.getAttribute("info").equals("choose another login");
                req.getRequestDispatcher("/start").forward(req, res);
            }
        }
    }
}