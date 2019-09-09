package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@WebServlet(name = "PersonalPageServlet", urlPatterns = {"/user"})
public class PersonalPageServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        String pass = (String) session.getAttribute("password");
        User user = userService.getUserByLoginAndPass(login, pass);
        Collection<User> list = new ArrayList<>();
        list.add(user);
        request.setAttribute("list", list);
        request.getRequestDispatcher("jsp/personalPage.jsp").forward(request, response);
    }
}