package in.tech.blogger.controller.json;

import in.tech.blogger.constant.Constants;
import in.tech.blogger.domain.User;
import in.tech.blogger.modal.UserModel;
import in.tech.blogger.query.UserQuery;
import in.tech.blogger.service.RoleService;
import in.tech.blogger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserJsonController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @RequestMapping("/list")
    @Secured(value = {Constants.ADMIN_ROLE, Constants.AUTHOR_ROLE})
    ResponseEntity<Map<String, Object>> list(@ModelAttribute UserQuery userQuery) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users", userService.search(userQuery));
        responseMap.put("total", userService.count(userQuery));
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping("/delete")
    @Secured(value = {Constants.ADMIN_ROLE})
    ResponseEntity<Map<String, Object>> delete(@RequestParam String id) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("status", userService.delete(id));
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping("/findBy")
    @Secured(value = {Constants.ADMIN_ROLE, Constants.AUTHOR_ROLE})
    ResponseEntity<Map<String, Object>> findBy(@RequestParam String email) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("user", userService.findBy(email));
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Secured(value = {Constants.ADMIN_ROLE, Constants.AUTHOR_ROLE})
    ResponseEntity<Map<String, Object>> save(@RequestBody UserModel userModel) {
        Map<String, Object> responseMap = new HashMap<>();
        User user = userService.save(userModel);
        responseMap.put("status", user != null);
        responseMap.put("user", user);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    @Secured(value = {Constants.ADMIN_ROLE, Constants.AUTHOR_ROLE})
    ResponseEntity<Map<String, Object>> roles() {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("roles", roleService.findAll());
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }
}
