package in.tech.blogger.controller;

import in.tech.blogger.domain.Category;
import in.tech.blogger.modal.CategoryModal;
import in.tech.blogger.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/list")
    ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("/category/list");
        List<Category> categories = categoryService.findAll();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @RequestMapping(value = "/create")
    String create() {
        return "/category/create";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    ModelAndView save(@ModelAttribute CategoryModal categoryModal) {
        ModelAndView modelAndView = new ModelAndView();
        if (categoryService.save(categoryModal)) {
            modelAndView.setViewName("redirect:/category/list");
        } else {
            modelAndView.setViewName("/category/create");
        }
        modelAndView.addObject("category", categoryModal);
        return modelAndView;
    }
}
