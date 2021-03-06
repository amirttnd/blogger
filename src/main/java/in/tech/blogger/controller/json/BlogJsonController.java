package in.tech.blogger.controller.json;

import in.tech.blogger.constant.Constants;
import in.tech.blogger.domain.Blog;
import in.tech.blogger.modal.BlogModel;
import in.tech.blogger.query.BlogQuery;
import in.tech.blogger.service.BlogService;
import in.tech.blogger.vo.BlogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static in.tech.blogger.util.Utils.pages;

@RestController
@RequestMapping("/api/blog")
public class BlogJsonController {

    @Autowired
    BlogService blogService;


    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @Secured(value = {Constants.ADMIN_ROLE, Constants.AUTHOR_ROLE})
    ResponseEntity<Map<String, Object>> save(@RequestBody BlogModel blogModel, Principal principal) {
        Map<String, Object> responseMap = new LinkedHashMap<>();
        blogModel.setUsername(principal.getName());
        Blog blog = blogService.save(blogModel);
        responseMap.put("status", blog != null);
        responseMap.put("blog", blog);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/partialUpdate", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @Secured(value = {Constants.ADMIN_ROLE, Constants.AUTHOR_ROLE})
    ResponseEntity<Map<String, Object>> partialUpdate(@RequestBody BlogModel blogModel, Principal principal) {
        Map<String, Object> responseMap = new LinkedHashMap<>();
        Blog blog = blogService.partialUpdate(blogModel);
        responseMap.put("status", blog != null);
        responseMap.put("blog", blog);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> list(@ModelAttribute BlogQuery blogQuery) {
        Map<String, Object> responseMap = new LinkedHashMap<>();
        List<BlogVO> blogs = blogService.search(blogQuery);
        long total = blogService.count(blogQuery);
        responseMap.put("blogs", blogs);
        responseMap.put("total", total);
        responseMap.put("pages", pages(blogQuery.getMax(), total));
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> get(@RequestParam String id) {
        Map<String, Object> responseMap = new LinkedHashMap<>();
        Blog blog = blogService.findById(id);
        responseMap.put("status", blog != null);
        responseMap.put("blog", blog);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/toggleRecommendation", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @Secured(value = {Constants.ADMIN_ROLE, Constants.AUTHOR_ROLE})
    ResponseEntity<Map<String, Object>> toggle(@RequestParam String id) {
        Map<String, Object> responseMap = new LinkedHashMap<>();
        Blog blog = blogService.toggleRecommendation(id);
        responseMap.put("status", blog != null);
        responseMap.put("blog", blog);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @Secured(value = {Constants.ADMIN_ROLE, Constants.AUTHOR_ROLE})
    ResponseEntity<Map<String, Object>> delete(@RequestParam String id) {
        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("status", blogService.delete(id));
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }
}
