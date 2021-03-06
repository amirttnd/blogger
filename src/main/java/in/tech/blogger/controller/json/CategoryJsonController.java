package in.tech.blogger.controller.json;


import in.tech.blogger.constant.Constants;
import in.tech.blogger.domain.Category;
import in.tech.blogger.modal.CategoryModel;
import in.tech.blogger.service.CategoryService;
import in.tech.blogger.vo.CategoryTreeVO;
import in.tech.blogger.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/addChild", method = RequestMethod.GET)
    @Secured(value = {Constants.ADMIN_ROLE, Constants.AUTHOR_ROLE})
    ResponseEntity<Map<String, Object>> addChild(@ModelAttribute CategoryModel categoryModel) {
        Map<String, Object> responseMap = new HashMap<>();
        Category category = categoryService.addChild(categoryModel);
        responseMap.put("status", category != null);
        responseMap.put("category", category);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    @Secured(value = {Constants.ADMIN_ROLE, Constants.AUTHOR_ROLE})
    ResponseEntity<Map<String, Object>> save(@ModelAttribute CategoryModel categoryModel) {
        Map<String, Object> responseMap = new HashMap<>();
        Category category = categoryService.save(categoryModel);
        responseMap.put("status", category != null);
        responseMap.put("category", category);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @Secured(value = {Constants.ADMIN_ROLE, Constants.AUTHOR_ROLE})
    ResponseEntity<Map<String, Object>> update(@ModelAttribute CategoryModel categoryModel) {
        Map<String, Object> responseMap = new HashMap<>();
        Category category = categoryService.update(categoryModel);
        responseMap.put("status", category != null);
        responseMap.put("category", category);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/partialUpdate", method = RequestMethod.POST)
    @Secured(value = {Constants.ADMIN_ROLE, Constants.AUTHOR_ROLE})
    ResponseEntity<Map<String, Object>> partialUpdate(@RequestBody CategoryModel categoryModel) {
        Map<String, Object> responseMap = new HashMap<>();
        Category category = categoryService.partialUpdate(categoryModel);
        responseMap.put("status", category != null);
        responseMap.put("category", category);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }


    @RequestMapping(value = "/toggle", method = RequestMethod.GET)
    @Secured(value = {Constants.ADMIN_ROLE, Constants.AUTHOR_ROLE})
    ResponseEntity<Map<String, Object>> toggle(@RequestParam String id) {
        Map<String, Object> responseMap = new HashMap<>();
        Category category = categoryService.toggle(id);
        responseMap.put("status", category != null);
        responseMap.put("category", category);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @Secured(value = {Constants.ADMIN_ROLE, Constants.AUTHOR_ROLE})
    ResponseEntity<Map<String, Boolean>> delete(@RequestParam String id) {
        Map<String, Boolean> responseMap = new HashMap<>();
        Boolean staus = categoryService.delete(id);
        responseMap.put("status", staus);
        return new ResponseEntity<Map<String, Boolean>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> show(@RequestParam String friendlyUrl, @RequestParam(defaultValue = "0") Integer page) {
        Map<String, Object> responseMap = new HashMap<>();
        CategoryVO categoryVO = categoryService.detail(friendlyUrl, page);
        responseMap.put("category", categoryVO);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }
}
