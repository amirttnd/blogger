package in.tech.blogger.controller.json;

import in.tech.blogger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserJsonController {

    @Autowired
    UserService userService;

    @RequestMapping("/list")
    ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users", userService.findAll());
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }
}
