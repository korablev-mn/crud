package servlet;

import service.DbServiceHibernate;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationUserServlet", urlPatterns = {"/user"})
public class RegistrationUserServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("list", userService.getAllUser());
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/user.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void init() throws ServletException {
//        userService.createTable();
    }
}