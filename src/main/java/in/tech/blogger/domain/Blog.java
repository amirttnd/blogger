package in.tech.blogger.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;


@Document(collection = "blog")
public class Blog {

    @Id
    String id;

    String shortHeading;

    @NotNull
    String title;

    String briefIntroduction;

    @NotNull
    String content;

    @DBRef
    Category category;

    @DBRef
    List<Category> relatedCategories;

    Boolean isPublished;

    List<String> tags;

    @DBRef
    List<Blog> relatedBlog;

    @DBRef
    User user;

}
