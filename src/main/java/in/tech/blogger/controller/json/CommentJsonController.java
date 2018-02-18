package in.tech.blogger.controller.json;

import in.tech.blogger.constant.Constants;
import in.tech.blogger.domain.Comment;
import in.tech.blogger.modal.CommentModel;
import in.tech.blogger.service.CommentService;
import in.tech.blogger.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comment")
public class CommentJsonController {


    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Map<String, Object>> save(@RequestBody CommentModel commentModel) {
        Map<String, Object> responseMap = new HashMap<>();
        Comment comment = commentService.save(commentModel);
        responseMap.put("status", comment != null);
        responseMap.put("comment", comment);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured(Constants.AUTHOR_ROLE)
    ResponseEntity<Map<String, Object>> delete(@PathVariable String id) {
        Map<String, Object> responseMap = new HashMap<>();
        Boolean status = commentService.delete(id);
        responseMap.put("status", status);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Map<String, Object>> get(@PathVariable String id) {
        Map<String, Object> responseMap = new HashMap<>();
        Comment comment = commentService.get(id);
        responseMap.put("comment", comment);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/findAllByReferenceId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Map<String, Object>> findAllByReferenceId(@RequestParam String referenceId) {
        Map<String, Object> responseMap = new HashMap<>();
        List<CommentVO> comments = commentService.findAllByReferenceId(referenceId);
        responseMap.put("comments", comments);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }
}
