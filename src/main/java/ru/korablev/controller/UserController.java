package ru.korablev.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import ru.korablev.model.CurrentProfile;
import ru.korablev.model.Role;
import ru.korablev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.korablev.service.FindProfileService;
import ru.korablev.service.RoleService;
import ru.korablev.service.UserService;
import ru.korablev.util.SecurityUtil;

import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(
            Model model,
            HttpSession session) {
        CurrentProfile profile = SecurityUtil.getCurrentProfile();
        session.setAttribute("info", " welcome ");
        if (profile != null) {
            model.addAttribute("inOut", "Logout");
            model.addAttribute("status", profile.getRole());
        } else {
            model.addAttribute("inOut", "Login");
            model.addAttribute("status", "guest");
        }
        model.addAttribute("info", session.getAttribute("info"));
        return "index";
    }

    @GetMapping("/admin")
    public String viewAllUsers(
            @AuthenticationPrincipal CurrentProfile profile,
            Model model,
            HttpSession session) {
        List<User> userList = userService.findAllUser();
        model.addAttribute("list", userList);
        if (profile != null) {
            model.addAttribute("inOut", "Logout");
            model.addAttribute("status", profile.getRole());
            session.setAttribute("info", profile.getName() + " - " + (session.getAttribute("info")==null ? "" : session.getAttribute("info")));
        } else {
            model.addAttribute("inOut", "Login");
            model.addAttribute("status", "guest");
        }
        model.addAttribute("info", session.getAttribute("info"));
        session.removeAttribute("info");
        return "user";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addUser(
            HttpSession session,
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
        session.setAttribute("info", " user +");
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
            Set<Role> roles = new HashSet<Role>();
            Role roleUser = roleService.getRoleByName(role);
            roles.add(roleUser);
            Date data = date.equals("") ? null : java.sql.Date.valueOf(date);
            User user = new User(Long.valueOf(id), login, name, pass, data, roles);
            userService.updateUserById(user);
            session.setAttribute("info", " user update");
            return "redirect:/admin";
        }
    }

    @RequestMapping(value = "/admin/del", method = RequestMethod.POST)
    public String delUser(
            HttpSession session,
            @RequestParam(value = "id") String id
    ) {
        userService.deleteUserFromID(Long.valueOf(id));
        session.setAttribute("info", " deleted user ");
        return "redirect:/admin";
    }

    @GetMapping(value = "/user")
    public String personalPage(
            @AuthenticationPrincipal CurrentProfile profile,
            Model model,
            HttpSession session) {
        if (profile != null) {
            model.addAttribute("inOut", "Logout");
            model.addAttribute("status", profile.getRole());
            session.setAttribute("info", profile.getName() + " - " + (session.getAttribute("info")==null ? "" : session.getAttribute("info")));
        } else {
            model.addAttribute("inOut", "Login");
            model.addAttribute("status", "guest");
        }
        Long id = profile.getId();
        User user = userService.getUserById(id);
        List<User> list = new ArrayList<>();
        list.add(user);
        model.addAttribute("list", list);
        model.addAttribute("info", session.getAttribute("info"));
        session.removeAttribute("info");
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
            Set<Role> roles = new HashSet<Role>();
            Role roleUser = roleService.getRoleByName(role);
            roles.add(roleUser);
            Date data = date.equals("") ? null : java.sql.Date.valueOf(date);
            User user = new User(Long.valueOf(id), login, name, pass, data, roles);
            userService.updateUserById(user);
            session.setAttribute("info", " user update");
        }
        return "redirect:/user";
    }
}