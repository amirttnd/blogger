package in.tech.blogger.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

    @RequestMapping(value = {"/login.html", "/login"})
    String login() {
        return "/login/login";
    }
}
