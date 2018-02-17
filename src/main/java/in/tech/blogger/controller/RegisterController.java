package in.tech.blogger.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

    @RequestMapping(value = {"/login.html", "/login"})
    String login() {
        return "/register/login";
    }

    @RequestMapping(value = {"/signup.html", "/signup"})
    String register() {
        return "/register/signup";
    }

    @RequestMapping(value = {"/forgotPassword.html", "/forgotPassword"})
    String forgotPassword() {
        return "/register/forgotPassword";
    }

    @RequestMapping(value = {"/resetPassword.html", "/resetPassword"})
    String resetPassword() {
        return "/register/resetPassword";
    }
}
