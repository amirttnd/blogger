package in.tech.blogger.controller.json;

import in.tech.blogger.domain.Blog;
import in.tech.blogger.modal.BlogModel;
import in.tech.blogger.query.BlogQuery;
import in.tech.blogger.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/blog")
public class BlogJsonController {

    @Autowired
    BlogService blogService;


    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> save(@RequestBody BlogModel blogModel) {
        System.out.println(blogModel.toString());
        Map<String, Object> responseMap = new LinkedHashMap<>();
        Blog blog = blogService.save(blogModel);
        responseMap.put("status", blog != null);
        responseMap.put("blog", blog);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> list(@ModelAttribute BlogQuery blogQuery) {
        Map<String, Object> responseMap = new LinkedHashMap<>();
        blogQuery.setOnlyPublished(null);
        List<Blog> blogs = blogService.search(blogQuery);
        responseMap.put("blogs", blogs);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> list(@RequestParam String id) {
        Map<String, Object> responseMap = new LinkedHashMap<>();
        Blog blog = blogService.findById(id);
        responseMap.put("status", blog != null);
        responseMap.put("blog", blog);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }
}
