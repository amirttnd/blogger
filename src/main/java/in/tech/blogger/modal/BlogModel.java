package in.tech.blogger.modal;

import java.util.List;

public class BlogModel {


    String id;

    String shortHeading;

    String title;

    String briefIntroduction;

    String content;

    String categoryId;

    List<String> relatedCategories;

    Boolean isPublished;

    List<String> tags;

    Boolean isRecommended;

    String username;

    Short rank;


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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getRelatedCategories() {
        return relatedCategories;
    }

    public void setRelatedCategories(List<String> relatedCategories) {
        this.relatedCategories = relatedCategories;
    }

    public Boolean getIsPublished() {
        if (isPublished == null) {
            return false;
        }
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

    public Boolean getIsRecommended() {
        return isRecommended;
    }

    public void setIsRecommended(Boolean isRecommended) {
        this.isRecommended = isRecommended;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Short getRank() {
        return rank;
    }

    public void setRank(Short rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "BlogModel{" +
                "id='" + id + '\'' +
                ", shortHeading='" + shortHeading + '\'' +
                ", title='" + title + '\'' +
                ", briefIntroduction='" + briefIntroduction + '\'' +
                ", content='" + content + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", relatedCategories=" + relatedCategories +
                ", isPublished=" + isPublished +
                ", tags=" + tags +
                ", isRecommended=" + isRecommended +
                '}';
    }
}
