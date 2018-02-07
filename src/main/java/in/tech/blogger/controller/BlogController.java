package in.tech.blogger.controller;

import in.tech.blogger.service.BlogService;
import in.tech.blogger.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class BlogController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    BlogService blogService;

    @RequestMapping("/blog/edit")
    String edit() {
        return "/blog/edit";
    }

    @RequestMapping("/blog/list")
    String list() {
        return "/blog/list";
    }

    @RequestMapping("/blog")
    String index() {
        return "/blog/home";
    }

    @RequestMapping("/{friendlyUrl}")
    ModelAndView show(@PathVariable String friendlyUrl) {
        ModelAndView modelAndView = new ModelAndView("/blog/show");
        modelAndView.addObject("blog", blogService.findAndIncView(friendlyUrl));
        return modelAndView;
    }

}
