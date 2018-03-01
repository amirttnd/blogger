package in.tech.blogger.controller.json;

import in.tech.blogger.domain.Contact;
import in.tech.blogger.modal.ContactModel;
import in.tech.blogger.modal.PageModel;
import in.tech.blogger.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
public class ContactJsonController {

    @Autowired
    ContactService contactService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    ResponseEntity<Map<String, Object>> save(@RequestBody ContactModel contactModel) {
        Map<String, Object> responseMap = new HashMap<>();
        Contact contact = contactService.save(contactModel);
        responseMap.put("status", contact != null);
        responseMap.put("contact", contact);
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> list(@ModelAttribute PageModel pageModel) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("contacts", contactService.findAll(new PageRequest(pageModel.getPage()-1, pageModel.getMax())));
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }
}

















