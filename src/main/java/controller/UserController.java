package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(
            Model model,
            HttpSession session) {
        session.setAttribute("status", session.isNew() ? "user" : session.getAttribute("status"));
        session.setAttribute("inOut", session.isNew() ? "Login" : session.getAttribute("inOut"));
        session.setAttribute("info", session.isNew() ? "welcome" : session.getAttribute("info"));
        model.addAttribute("status", session.getAttribute("status"));
        model.addAttribute("inOut", session.getAttribute("inOut"));
        model.addAttribute("info", session.getAttribute("info"));
        return "index";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/admin")
    public String viewAllUsers(
            Model model,
            HttpSession session) {
        List<User> userList = userService.findAllUser();
        model.addAttribute("list", userList);
        model.addAttribute("status", session.getAttribute("status"));
        model.addAttribute("inOut", session.getAttribute("inOut"));
        model.addAttribute("info", session.getAttribute("info"));
        return "user";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addUser(
            @RequestParam(value = "login") String login,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "text", required = false) String name,
            @RequestParam(value = "date", required = false) String date
    ) {
        Date data = date.equals("") ? null : java.sql.Date.valueOf(date);
        User user = new User(login, name, password, data, "user");
        userService.addUser(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public String updateAdmin(
            HttpSession session,
            @RequestParam(value = "id") String id,
            @RequestParam(value = "login") String login,
            @RequestParam(value = "password") String pass,
            @RequestParam(value = "cmd") String value,
            @RequestParam(value = "text", required = false) String name,
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "role") String role
    ) {
        if (role.equals("")) {
            role = "user";
        }
        if (value.equals("DELETE")) {
            delUser(session, id);
            return "redirect:/admin";
        } else {
            Date data = date.equals("") ? null : java.sql.Date.valueOf(date);
            User user = new User(Long.valueOf(id), login, name, pass, data, role);
            userService.updateUserById(user);
            session.setAttribute("info", "user update");
            return "redirect:/admin";
        }
    }

    @RequestMapping(value = "/admin/del", method = RequestMethod.POST)
    public String delUser(
            HttpSession session,
            @RequestParam(value = "id") String id
    ) {
        userService.deleteUserFromID(Long.valueOf(id));
        session.setAttribute("info", "deleted");
        return "redirect:/admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String Login(
            HttpSession session,
            @RequestParam(value = "login") String login,
            @RequestParam(value = "password") String pass
    ) {
        User user = userService.getUserByLoginAndPassword(login, pass);
        if (user != null) {
            String name = (user.getName() == null) ? "guest" : user.getName();
            session.setMaxInactiveInterval(30 * 60);
            session.setAttribute("status", user.getRole());
            session.setAttribute("inOut", "Logout");
            session.setAttribute("info", "welcome : " + name);
            session.setAttribute("login", login);
            session.setAttribute("password", pass);
            session.setAttribute("role", user.getRole());
            return "redirect:/admin";
        } else
            session.setAttribute("info", "wrong login or password");
        return "redirect:";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String Logout(
            HttpSession session
    ) {
        session.removeAttribute("password");
        session.removeAttribute("login");
        session.removeAttribute("role");
        session.setAttribute("inOut", "Login");
        session.setAttribute("role", "user");
        session.setAttribute("status", "user");
        session.setAttribute("info", "welcome : ");
        return "redirect:/";
    }

    @GetMapping(value = "/user")
    public String personalPage(
            Model model,
            HttpSession session) {
        String login = (String) session.getAttribute("login");
        String pass = (String) session.getAttribute("password");
        User user = userService.getUserByLoginAndPassword(login, pass);
        List<User> list = new ArrayList<>();
        list.add(user);
        model.addAttribute("list", list);
        model.addAttribute("status", session.getAttribute("status"));
        model.addAttribute("info", session.getAttribute("info"));
        return "personalPage";
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String updateUser(
            HttpSession session,
            @RequestParam(value = "id") String id,
            @RequestParam(value = "login") String login,
            @RequestParam(value = "password") String pass,
            @RequestParam(value = "cmd") String value,
            @RequestParam(value = "text", required = false) String name,
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "role") String role
    ) {
        if (role.equals("")) {
            role = "user";
        }
        if (value.equals("DELETE")) {
            session.setAttribute("info", "you do not have sufficient privileges");
        } else {
            Date data = date.equals("") ? null : java.sql.Date.valueOf(date);
            User user = new User(Long.valueOf(id), login, name, pass, data, "user");
            userService.updateUserById(user);
            session.setAttribute("info", "user update");
            session.setAttribute("login", login);
            session.setAttribute("password", pass);
            session.setAttribute("info", "user update");
        }
        return "redirect:/user";
    }
}