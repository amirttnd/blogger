package in.tech.blogger.controller;

import in.tech.blogger.constant.Constants;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@Secured(Constants.ADMIN_ROLE)
public class AdminController {

    @RequestMapping("/index")
    String index() {
        return "/admin/index";
    }
}
