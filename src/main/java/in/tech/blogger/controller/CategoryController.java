package in.tech.blogger.controller;

import in.tech.blogger.modal.CategoryModel;
import in.tech.blogger.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/edit")
    ModelAndView edit(@ModelAttribute CategoryModel categoryModel) {
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/list")
    ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("/category/tree");
        return modelAndView;
    }

    @RequestMapping(value = "/{friendlyUrl}")
    ModelAndView blogs(@PathVariable String friendlyUrl) {
        ModelAndView modelAndView = new ModelAndView("/category/blogs");
        return modelAndView;
    }
}
