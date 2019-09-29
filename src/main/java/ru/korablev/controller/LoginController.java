package ru.korablev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.korablev.model.CurrentProfile;
import ru.korablev.model.User;
import ru.korablev.service.UserService;
import ru.korablev.util.SecurityUtil;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String Login(
            HttpSession session,
            Model model,
            @RequestParam(value = "login") String login
    ) {
        CurrentProfile profile = SecurityUtil.getCurrentProfile();
        if (profile != null) {
            String name = (profile.getName() == null) ? "guest" : profile.getName();
            session.setMaxInactiveInterval(30 * 60);
            session.setAttribute("info", "welcome : " + name);
            model.addAttribute("info", "welcome : " + name);
            if(profile !=null) {
                model.addAttribute("inOut", "Logout");
                model.addAttribute("status", profile.getRoleString());
            } else {
                model.addAttribute("inOut", "Login");
                model.addAttribute("status", "guest");
            }
            return "/user";
        } else
            session.setAttribute("info", "wrong login or password");
        return "redirect:/";
    }

    @RequestMapping(value = "/login-error")
    public String LoginError(HttpSession session){
        if(session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") == null){
            return "redirect:";
        }
        return "index";
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

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
}
