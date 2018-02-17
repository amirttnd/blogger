package in.tech.blogger.controller;


import in.tech.blogger.modal.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/doSignUp", method = RequestMethod.POST)
    String doSignUp(@RequestBody UserModel userModel) {
        return "/register/resetPassword";
    }

}
