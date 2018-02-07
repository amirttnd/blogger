package in.tech.blogger.vo;


import in.tech.blogger.domain.Blog;
import in.tech.blogger.domain.Category;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryVO {

    String id;
    String name;
    String friendlyUrl;

    Boolean active;

    Integer level;

    CategoryVO parent;

    Date dateCreated;
    Date lastUpdated;

    List<Blog> blogs;

    public CategoryVO() {

    }

    public CategoryVO(Category category) {
        bind(category);
    }

    public void bind(Category category) {
        if (category != null) {
            this.id = category.getId();
            this.name = category.getName();
            this.friendlyUrl = category.getFriendlyUrl();
            this.active = category.getActive();
            this.level = category.getLevel();
            if (category.getParent() != null) {
                this.parent = category.getParent().toCategoryVO();
            }
            this.dateCreated = category.getDateCreated();
            this.lastUpdated = category.getLastUpdated();
        }
    }

    public static List<CategoryVO> toCategoryVO(List<Category> categories) {
        if (categories != null) {
            return categories.stream().map((category) -> {
                return new CategoryVO(category);
            }).collect(Collectors.toList());
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public CategoryVO getParent() {
        return parent;
    }

    public void setParent(CategoryVO parent) {
        this.parent = parent;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public String getFriendlyUrl() {
        return friendlyUrl;
    }

    public void setFriendlyUrl(String friendlyUrl) {
        this.friendlyUrl = friendlyUrl;
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
