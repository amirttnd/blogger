package in.tech.blogger.controller;

import in.tech.blogger.constant.Constants;
import in.tech.blogger.modal.CategoryModel;
import in.tech.blogger.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/edit")
    @Secured(Constants.ADMIN_ROLE)
    ModelAndView edit(@ModelAttribute CategoryModel categoryModel) {
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/tree")
    @Secured(Constants.ADMIN_ROLE)
    ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("/category/tree");
        return modelAndView;
    }

    @RequestMapping(value = "/show")
    @Secured(Constants.ADMIN_ROLE)
    ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("/category/admin/show");
        return modelAndView;
    }
}
