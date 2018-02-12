package in.tech.blogger.controller;

import in.tech.blogger.domain.Blog;
import in.tech.blogger.query.BlogQuery;
import in.tech.blogger.service.BlogService;
import in.tech.blogger.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @RequestMapping(value = {"/blog", "/blog.html", "/blogs", "/blogs.html", "/search.html", "/search"})
    ModelAndView index(@ModelAttribute BlogQuery blogQuery) {
        ModelAndView modelAndView = new ModelAndView("/blog/blogs");
        blogQuery.setOnlyPublished(true);

        List<Blog> blogs = blogService.search(blogQuery);

        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @RequestMapping("/blog/{friendlyUrl}")
    ModelAndView show(@PathVariable String friendlyUrl) {
        ModelAndView modelAndView = new ModelAndView("/blog/show");
        modelAndView.addObject("blogVO", blogService.findAndIncView(friendlyUrl));
        return modelAndView;
    }

}
