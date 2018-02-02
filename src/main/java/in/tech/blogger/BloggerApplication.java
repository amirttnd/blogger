package in.tech.blogger;

import in.tech.blogger.domain.Category;
import in.tech.blogger.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class BloggerApplication implements CommandLineRunner {

    @Autowired
    CategoryRepository categoryRepository;

    Logger logger = LoggerFactory.getLogger(BloggerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BloggerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Category parent = new Category();
//        parent.setName("java");
//        parent.setLevel(1);
//        categoryRepository.save(parent);
//        Category child = new Category();
//        child.setName("inheritance");
//        child.setLevel(2);
//        child.setParent(parent);
//
//        categoryRepository.save(child);
//
//        Category grandChild = new Category();
//        grandChild.setName("multiple inheritance");
//        grandChild.setLevel(3);
//        grandChild.setParent(child);
//        categoryRepository.save(grandChild);

        for (Category category : categoryRepository.getCategoryParents()) {
            System.out.println(category.toString());
        }
    }
}
