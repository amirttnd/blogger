package in.tech.blogger.controller;

import in.tech.blogger.service.CategoryService;
import in.tech.blogger.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class BlogController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping({"/", "/blogs", "/blogs.html"})
    ModelAndView index(@RequestParam(required = false) Long categoryId) {
        ModelAndView modelAndView = new ModelAndView("/blog/index");
        List<CategoryVO> categories = CategoryVO.toCategoryVO(categoryService.findAll());
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }


    @RequestMapping(value = "/blog/list")
    String list() {
        return "/blog/list";
    }

    @RequestMapping("/blog/{friendlyUrl}")
    String show(@PathVariable String friendlyUrl) {
        return "/blog/show";
    }

    @RequestMapping("/blog/create")
    String create() {
        return "/blog/create";
    }
}
