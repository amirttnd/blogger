package in.tech.blogger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BlogController {

    @RequestMapping({"/", "/blogs", "/blogs.html"})
    String list() {
        return "/blog/list";
    }

    @RequestMapping("/blog/{friendlyUrl}")
    String show(@PathVariable String friendlyUrl) {
        return "/blog/show";
    }
}
