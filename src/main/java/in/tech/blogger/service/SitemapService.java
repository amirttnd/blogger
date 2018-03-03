package in.tech.blogger.service;

import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import in.tech.blogger.domain.Blog;
import in.tech.blogger.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

@Service
public class SitemapService {
    private static final String HOST = "http://www.justbeginme.com";

    @Autowired
    BlogRepository blogRepository;

    public void generate() throws Exception {
        File sitemap = new File("/tmp");
        WebSitemapGenerator webSitemapGenerator = new WebSitemapGenerator(HOST, sitemap);
        WebSitemapUrl url = null;
        for (Blog blog : blogRepository.findAll()) {
            url = new WebSitemapUrl.Options(HOST + "/blog/" + blog.getFriendlyUrl())
                    .lastMod(new Date())
                    .priority(1.0)
                    .changeFreq(ChangeFreq.DAILY)
                    .build();

            webSitemapGenerator.addUrl(url);
        }

        webSitemapGenerator.addUrl(HOST + "/about.html");
        webSitemapGenerator.addUrl(HOST + "/contact.html");
        webSitemapGenerator.addUrl(HOST + "/privacy.html");

        webSitemapGenerator.write();
    }
}
