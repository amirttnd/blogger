package in.tech.blogger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @RequestMapping(value = "/list")
    String list() {
        return "/category/list";
    }

    @RequestMapping(value = "/create")
    String create() {
        return "/category/create";
    }
}
