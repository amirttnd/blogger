package in.tech.blogger.controller;

import in.tech.blogger.domain.Blog;
import in.tech.blogger.modal.BlogModel;
import in.tech.blogger.service.BlogService;
import in.tech.blogger.service.CategoryService;
import in.tech.blogger.vo.BlogVO;
import in.tech.blogger.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class BlogController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    BlogService blogService;

    @RequestMapping({"/", "/blogs", "/blogs.html"})
    ModelAndView index(@RequestParam(required = false) Long categoryId) {
        ModelAndView modelAndView = new ModelAndView("/blog/index");
        List<CategoryVO> categories = CategoryVO.toCategoryVO(categoryService.findAll());
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @RequestMapping({"/blog/show", "/blog/show.html"})
    ModelAndView show(@RequestParam(required = false) Long id) {
        ModelAndView modelAndView = new ModelAndView("/blog/show");
        modelAndView.addObject("blog", new BlogVO(blogService.findById(id)));
        return modelAndView;
    }


    @RequestMapping(value = "/admin/blog/list")
    ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("/blog/admin/list");
        List<Blog> blogs = blogService.findAll();
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @RequestMapping("/admin/blog/edit")
    ModelAndView edit(@RequestParam(required = false) Long id) {
        ModelAndView modelAndView = new ModelAndView("/blog/admin/edit");
        List<CategoryVO> categories = CategoryVO.toCategoryVO(categoryService.findAll());
        BlogVO blogVO = new BlogVO(blogService.findById(id));
        modelAndView.addObject("blog", blogVO);
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @RequestMapping(value = "/blog/save", method = RequestMethod.POST)
    ModelAndView save(@ModelAttribute BlogModel blogModel) {
        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        if (blogService.save(blogModel)) {
            modelAndView.setViewName("redirect:/blog/list");
        } else {
            modelAndView.addObject("blog", new BlogVO(blogModel));
        }
        return modelAndView;
    }
}
