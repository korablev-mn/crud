package servlet;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UpdateAdminServlet", urlPatterns = {"/admin/update"})
public class UpdateAdminServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String id = request.getParameter("id");
        String login = request.getParameter("login");
        String value = request.getParameter("cmd");
        String name = request.getParameter("text");
        String pass = request.getParameter("password");
        String date = request.getParameter("date");
        String role = request.getParameter("role");

        if (date.equals("")) {
            date = "1900-12-12";
        }
        if (role.equals("")) {
            role = "user";
        }
        if (id.equals("") || login.equals("") || pass.equals("")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else if (value.equals("DELETE")) {
            request.getRequestDispatcher("/admin/del").forward(request, response);
        } else {
            User user = new User(Long.valueOf(id), login, name, pass, java.sql.Date.valueOf(date), role);
            userService.updateUser(user);
            session.setAttribute("info", "user update");
            response.sendRedirect("/admin");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}