package in.tech.blogger;

import in.tech.blogger.service.BootstrapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableMongoRepositories
@EnableMongoAuditing
@EnableAspectJAutoProxy
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class BloggerApplication implements CommandLineRunner {

    @Autowired
    BootstrapService bootstrapService;

    Logger logger = LoggerFactory.getLogger(BloggerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BloggerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        bootstrapService.initializeRole();
    }
}
