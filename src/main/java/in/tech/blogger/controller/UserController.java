package in.tech.blogger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/list")
    String list() {
        return "/user/list";
    }

    @RequestMapping("/edit")
    String edit() {
        return "/user/edit";
    }
}
