package in.tech.blogger.controller;

import in.tech.blogger.constant.Constants;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/list")
    @Secured(Constants.ADMIN_ROLE)
    String list() {
        return "/user/list";
    }

    @RequestMapping("/edit")
    @Secured(Constants.ADMIN_ROLE)
    String edit() {
        return "/user/edit";
    }
}
