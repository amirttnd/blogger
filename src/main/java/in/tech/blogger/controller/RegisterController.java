package in.tech.blogger.controller;


import in.tech.blogger.modal.UserModel;
import in.tech.blogger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/login.html", "/login"})
    String login() {
        return "/register/login";
    }

    @RequestMapping(value = {"/signup.html", "/signup"})
    String register(HttpSession session) {
        if (session.getAttribute("user") == null) {
            session.setAttribute("user", new UserModel());
        }
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

    @RequestMapping(value = {"/403"})
    String accessDenied() {
        return "/register/accessDenied";
    }


    @RequestMapping(value = "/doSignUp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ModelAndView doSignUp(@ModelAttribute UserModel user, RedirectAttributes redirectAttributes, HttpSession session) {
        String redirectUrl = "redirect:/signup.html";
        if (user.getEmail() != null && user.getEmail().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Email address is required");
        } else if (!user.isSamePassword()) {
            redirectAttributes.addFlashAttribute("error", "Password and repeat password does not match");
        } else {
            if (userService.save(user) != null) {
                redirectUrl = "redirect:/blogs.html";
            }
        }
        session.setAttribute("user", user);
        return new ModelAndView(redirectUrl);
    }

}
