package in.tech.blogger.domain;


import in.tech.blogger.modal.BlogModel;
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

    public Blog() {

    }

    public Blog(BlogModel blogModel) {
        bind(blogModel);
    }

    public void bind(BlogModel blogModel) {
        if (blogModel != null) {
            shortHeading = blogModel.getShortHeading();
            title = blogModel.getTitle();
            briefIntroduction = blogModel.getBriefIntroduction();
            content = blogModel.getContent();
            tags = blogModel.getTags();
            isPublished = blogModel.getIsPublished();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortHeading() {
        return shortHeading;
    }

    public void setShortHeading(String shortHeading) {
        this.shortHeading = shortHeading;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getRelatedCategories() {
        return relatedCategories;
    }

    public void setRelatedCategories(List<Category> relatedCategories) {
        this.relatedCategories = relatedCategories;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Blog> getRelatedBlog() {
        return relatedBlog;
    }

    public void setRelatedBlog(List<Blog> relatedBlog) {
        this.relatedBlog = relatedBlog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
