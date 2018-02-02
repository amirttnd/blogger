package in.tech.blogger.modal;

import in.tech.blogger.domain.Category;

import java.util.List;

public class BlogModel {


    String id;

    String shortHeading;

    String title;

    String briefIntroduction;

    String content;

    String categoryId;

    List<Category> relatedCategories;

    Boolean isPublished;

    List<String> tags;

    List relatedBlog;

}
