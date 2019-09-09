package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "AddingUserServlet", urlPatterns = {"/admin/add"})
public class AddingUserServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String name = request.getParameter("text");
        String date = request.getParameter("date");

        if (login.equals("") || pass.equals("")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            session.setAttribute("info", "login and pass not empty");
        } else{
            if (date.equals("")) {
                date = "1900-12-12";
            }
            User user = new User(login, name, pass, Date.valueOf(date), "user");
            if (userService.isUserExist(user)){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                session.setAttribute("info", "choose another login");
            } else {
                if (userService.addUser(user)) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    session.setAttribute("info", "successfully");
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    session.setAttribute("info", "failure add user");
                }
            }
        }
        response.sendRedirect("/admin");
    }
}