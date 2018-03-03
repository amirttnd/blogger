package in.tech.blogger.controller;

import in.tech.blogger.domain.User;
import in.tech.blogger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/about.html", "/about"})
    ModelAndView aboutUs() {
        ModelAndView modelAndView = new ModelAndView("/aboutUs");
        List<User> users = userService.findAll();
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @RequestMapping(value = {"/contact.html", "/contact"})
    String contactUs() {

        return "/contact";
    }

    @RequestMapping(value = {"/privacy.html", "/privacy"})
    String privacy() {

        return "/privacy";
    }
}
