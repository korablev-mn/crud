package servlet;

import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteUserFromIDServlet", urlPatterns = {"/user/del"})

public class DeleteUserFromIDServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        if (id.equals("")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        Long nid = Long.valueOf(id);
        userService.deleteUserFromID(nid);
        response.sendRedirect("/user");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}