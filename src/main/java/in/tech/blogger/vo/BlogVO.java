package in.tech.blogger.vo;

import in.tech.blogger.domain.Blog;
import in.tech.blogger.modal.BlogModel;

import java.util.Date;

public class BlogVO {

    Long id;
    String title;
    String content;
    Boolean active;
    Date dateCreated;
    Date lastUpdated;
    CategoryVO category = new CategoryVO();

    public BlogVO(Blog blog) {
        if (blog != null) {
            this.id = blog.getId();
            this.title = blog.getTitle();
            this.content = blog.getContent();
            this.active = blog.getActive();
            this.dateCreated = blog.getDateCreated();
            this.lastUpdated = blog.getLastUpdated();
            this.category = new CategoryVO(blog.getCategory());
        }
    }

    public BlogVO(BlogModel blog) {
        if (blog != null) {
            this.id = blog.getId();
            this.title = blog.getTitle();
            this.content = blog.getContent();
            this.active = blog.getActive();
            this.getCategory().setId(blog.getCategoryId());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryVO getCategory() {
        return category;
    }

    public void setCategory(CategoryVO categoryVO) {
        this.category = categoryVO;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
