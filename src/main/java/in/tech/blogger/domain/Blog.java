package in.tech.blogger.domain;


import in.tech.blogger.modal.BlogModel;
import in.tech.blogger.util.Util;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Document(collection = "blog")
public class Blog implements Persistable<String> {

    @Id
    String id;

    @Indexed
    String shortHeading;

    @NotNull
    @Indexed
    String title;

    @Indexed
    String briefIntroduction;

    @NotNull
    String content;

    @DBRef
    Category category;

    @DBRef
    List<Category> relatedCategories;

    Boolean isPublished;

    @Indexed
    List<String> tags;

    @DBRef
    List<Blog> relatedBlog;

    @DBRef
    User user;

    @Indexed
    String friendlyUrl;

    @CreatedDate
    Date dateCreated;

    @LastModifiedDate
    Date lastUpdated;

    Long views = 0l;

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
            friendlyUrl = Util.toFriendlyURL(shortHeading);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return id == null;
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

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public void incViews() {
        this.views++;
    }

    public String getPrettyFormatViews() {
        return Util.getPrettyFormat(this.views);
    }

    public List<Category> getSortedCategories() {
        if (relatedCategories != null) {
            return relatedCategories.stream().sorted((category1, category2) -> {
                return category1.getName().compareTo(category2.getName());
            }).collect(Collectors.toList());
        }
        return Arrays.asList();
    }

    @Override
    public String toString() {
        return "Blog{" +
                "views=" + views +
                ", lastUpdated=" + lastUpdated +
                ", dateCreated=" + dateCreated +
                ", friendlyUrl='" + friendlyUrl + '\'' +
                ", user=" + user +
                ", relatedBlog=" + relatedBlog +
                ", tags=" + tags +
                ", isPublished=" + isPublished +
                ", relatedCategories=" + relatedCategories +
                ", category=" + category +
                ", content='" + content + '\'' +
                ", briefIntroduction='" + briefIntroduction + '\'' +
                ", title='" + title + '\'' +
                ", shortHeading='" + shortHeading + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
