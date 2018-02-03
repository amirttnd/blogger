package in.tech.blogger.controller.json;


import in.tech.blogger.service.CategoryService;
import in.tech.blogger.vo.CategoryTreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/api/category")
@RestController
public class CategoryJsonController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/tree", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Map<String, List<CategoryTreeVO>>> tree() {
        Map<String, List<CategoryTreeVO>> responseMap = new HashMap<String, List<CategoryTreeVO>>();
        responseMap.put("tree", categoryService.tree());
        return new ResponseEntity<Map<String, List<CategoryTreeVO>>>(responseMap, HttpStatus.OK);
    }
}
