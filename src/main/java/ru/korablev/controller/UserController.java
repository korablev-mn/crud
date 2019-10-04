package ru.korablev.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.korablev.model.Role;
import ru.korablev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.korablev.service.RoleService;
import ru.korablev.service.UserService;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/")
    public String home(
            @AuthenticationPrincipal String login,
            Model model,
            HttpSession session) {
        session.setAttribute("info", " welcome ");
        if (login != null) {
            model.addAttribute("inOut", "Login");
        } else {
            model.addAttribute("inOut", "Logout");
        }
        return "index";
    }

    @PostMapping(value = "/login")
    public String Login(
            @RequestParam(value = "login") String login
    ) {
        return "redirect:/";
    }

    @PostMapping(value = "/logout")
    public String Logout(
            HttpSession session,
            Model model) {
        session.removeAttribute("password");
        session.removeAttribute("login");
        session.removeAttribute("role");
        session.setAttribute("inOut", "Login");
        session.setAttribute("role", "user");
        session.setAttribute("status", "user");
        model.addAttribute("inOut", "Login");
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/admin")
    public String viewAllUsers(
            Model model) {
        List<User> userList = userService.findAllUser();
        model.addAttribute("list", userList);
        return "user";
    }

    @PostMapping(value = "/admin/add")
    public String addUser(
            @RequestParam(value = "login") String login,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "text", required = false) String name,
            @RequestParam(value = "date", required = false) String date
    ) {
        Date data = date.equals("") ? null : java.sql.Date.valueOf(date);
        Set<Role> roles = new HashSet<>();
        Role role = roleService.getRoleByName("user");
        roles.add(role);
        User user = new User(login, name, password, data, roles);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/update")
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
            delUser(id);
            return "redirect:/admin";
        } else {
            Set<Role> roles = new HashSet<Role>();
            Role roleUser = roleService.getRoleByName(role);
            roles.add(roleUser);
            Date data = date.equals("") ? null : java.sql.Date.valueOf(date);
            User user = new User(Long.valueOf(id), login, name, pass, data, roles);
            userService.updateUserById(user);
            return "redirect:/admin";
        }
    }

    @PostMapping(value = "/admin/del")
    public String delUser(
            @RequestParam(value = "id") String id
    ) {
        userService.deleteUserFromID(Long.valueOf(id));
        return "redirect:/admin";
    }

    @GetMapping(value = "/user")
    public String personalPage(
           Principal profile,
            Model model) {
        String login = profile.getName();
        User user = userService.getUserByLogin(login);
        List<User> list = new ArrayList<>();
        list.add(user);
        model.addAttribute("list", list);
        return "personalPage";
    }

    @PostMapping(value = "/user/update")
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
            Set<Role> roles = new HashSet<Role>();
            Role roleUser = roleService.getRoleByName(role);
            roles.add(roleUser);
            Date data = date.equals("") ? null : java.sql.Date.valueOf(date);
            User user = new User(Long.valueOf(id), login, name, pass, data, roles);
            userService.updateUserById(user);
        }
        return "redirect:/user";
    }
}