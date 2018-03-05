package in.tech.blogger.controller;

import in.tech.blogger.constant.Constants;
import in.tech.blogger.query.BlogQuery;
import in.tech.blogger.service.BlogService;
import in.tech.blogger.service.CategoryService;
import in.tech.blogger.vo.BlogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    @Secured(value = {Constants.ADMIN_ROLE, Constants.AUTHOR_ROLE})
    String edit() {
        return "/blog/edit";
    }

    @RequestMapping("/blog/list")
    @Secured(value = {Constants.ADMIN_ROLE, Constants.AUTHOR_ROLE})
    String list() {
        return "/blog/list";
    }

    @RequestMapping(value = {"/", "/blog", "/blog.html", "/blogs", "/blogs.html", "/search.html", "/search"})
    ModelAndView index(@ModelAttribute BlogQuery blogQuery) {
        ModelAndView modelAndView = new ModelAndView("/blog/blogs");
        return modelAndView;
    }

    @RequestMapping("/blog/{friendlyUrl}")
    ModelAndView show(@PathVariable String friendlyUrl) {
        ModelAndView modelAndView = new ModelAndView("/blog/show");
        BlogVO blogVO = blogService.findAndIncView(friendlyUrl);
        modelAndView.addObject("blogVO", blogVO);
        modelAndView.addObject("rootCategory", categoryService.getRootCategory(blogVO.getCategory()));

        return modelAndView;
    }
}
