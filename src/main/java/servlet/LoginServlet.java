package servlet;

import model.User;
import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        User user = userService.getUserByLoginAndPass(login, pass);

        HttpSession session = request.getSession();
        if(user != null) {
            session.setMaxInactiveInterval(30 * 60);

            Cookie cookie = new Cookie("crudProject", login);
            cookie.setMaxAge(30 * 60);
            response.addCookie(cookie);

            session.setAttribute("status", user.getRole());
            session.setAttribute("inOut", "Logout");
            session.setAttribute("info", "welcome : " + user.getName());
            session.setAttribute("login", login);
            session.setAttribute("password", pass);
            session.setAttribute("role", user.getRole());

            response.sendRedirect("/admin");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            session.setAttribute("info", "wrong login or password");
            response.sendRedirect("/start");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}