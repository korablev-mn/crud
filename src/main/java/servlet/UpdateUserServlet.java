package servlet;

import model.User;
import service.DbServiceHibernate;
import service.UserService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateUserServlet", urlPatterns = {"/user/update"})
public class UpdateUserServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String value = request.getParameter("cmd");
        String name = request.getParameter("text");
        String pass = request.getParameter("password");
        String date = request.getParameter("date");

        if (id.equals("") || name.equals("") || pass.equals("") || date.equals("")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else if(value.equals("DELETE")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/user/del");
            dispatcher.forward(request, response);
        } else {
            User user = new User(Long.valueOf(id), name, pass, java.sql.Date.valueOf(date));
            userService.updateUser(user);
            response.sendRedirect("/user");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}