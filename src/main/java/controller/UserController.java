package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.UserService;

import java.sql.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/")
//    public String getHomePage() {
//        return "redirect:/index";
//    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addUser(
            @RequestParam(value = "login") String login,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "birthday", required = false) Date birthday,
            @RequestParam(value = "role", required = false) String role
    ) {
        User user = new User(login,name,password,birthday,"user");
        userService.addUser(user);
        return "user";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("info", "Welcome");
        model.addAttribute("status", "user");
        model.addAttribute("inOut", "Login");
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String Login(
            Model model,
            @RequestParam(value = "login") String login,
            @RequestParam(value = "password") String pass
    ) {
        model.addAttribute("inOut", "Logout");
        User user = userService.getUserByLoginAndPassword(login, pass);
        if(user!=null && user.getRole().equals("admin")){
            return "user";
        } else
        return "index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String Logout(Model model) {
        model.addAttribute("info", "Welcome");
        model.addAttribute("status", "user");
        model.addAttribute("inOut", "Login");
        return "index";
    }

    @GetMapping("/admin")
    public String viewAllUsers(Model model) {
        List<User> userList = userService.findAllUser();
        model.addAttribute("list", userList);
        return "user";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

//    @RequestMapping(value = "/all", method = RequestMethod.GET)
//    public String homes() {
//        return "admin/home";
//    }
//
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public ModelAndView addUser(
//            @RequestParam(value = "login") String login,
//            @RequestParam(value = "password") String password,
//            @RequestParam(value = "name", required = false) String name,
//            @RequestParam(value = "birthday", required = false) Date birthday,
//            @RequestParam(value = "role", required = false) String role
//    ) {
//        User user = new User();
//        user.setLogin(login);
//        user.setPassword(password);
//        user.setName(name);
//        user.setBirthday(birthday);
//        user.setRole(role);
//        userService.addUser(user);
//        return new ModelAndView("user", "message", "user saved");
//    }
}