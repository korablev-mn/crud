package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "AddingUserServlet", urlPatterns = {"/user/add"})
public class AddingUserServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("text");
        String pass = request.getParameter("password");
        String date = request.getParameter("date");

        if (name.equals("") || pass.equals("") || date.equals("")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        User user = new User(name, pass, Date.valueOf(date));
        userService.addUser(user);
        response.sendRedirect("/user");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
