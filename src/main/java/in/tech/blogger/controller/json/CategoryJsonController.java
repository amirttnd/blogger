package in.tech.blogger.controller.json;


import in.tech.blogger.service.CategoryService;
import in.tech.blogger.vo.CategoryTreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/api/category")
@RestController
public class CategoryJsonController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/tree", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CategoryTreeVO>> tree() {
        return new ResponseEntity<List<CategoryTreeVO>>(categoryService.tree(), HttpStatus.OK);
    }
}
