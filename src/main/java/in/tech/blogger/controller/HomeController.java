package in.tech.blogger.controller;

import in.tech.blogger.domain.User;
import in.tech.blogger.service.SitemapService;
import in.tech.blogger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    SitemapService sitemapService;

    @RequestMapping(value = {"/about.html", "/about"})
    ModelAndView aboutUs() {
        ModelAndView modelAndView = new ModelAndView("/aboutUs");
        List<User> users = userService.findAllIsEnabled(true);
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @RequestMapping(value = {"/contact.html", "/contact"})
    String contactUs() {

        return "/contact";
    }

    @RequestMapping(value = {"/privacy.html", "/privacy"})
    String privacy() {

        return "/privacy";
    }

    @GetMapping("/sitemap.xml")
    public ResponseEntity<InputStreamResource> sitemap() throws IOException {

        try {
            sitemapService.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File("/tmp/sitemap.xml");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_XML).contentLength(file.length())
                .body(resource);
    }
}
