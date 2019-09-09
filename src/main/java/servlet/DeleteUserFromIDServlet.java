package servlet;

import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeleteUserFromIDServlet", urlPatterns = {"/admin/del"})

public class DeleteUserFromIDServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String id = request.getParameter("id");
        if (id.equals("")) {
            response.sendRedirect("/admin");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }else {
            Long nid = Long.valueOf(id);
            userService.deleteUserFromID(nid);
            session.setAttribute("info", "user deleted");
            response.sendRedirect("/admin");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}