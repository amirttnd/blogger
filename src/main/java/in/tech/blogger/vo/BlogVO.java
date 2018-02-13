package in.tech.blogger.vo;

import in.tech.blogger.domain.Blog;

import java.util.List;

public class BlogVO {
    Blog blog;
    Long comments;
    List<Blog> recommendations;

    public BlogVO() {

    }

    public BlogVO(Blog blog) {
        this.blog = blog;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public List<Blog> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Blog> recommendations) {
        this.recommendations = recommendations;
    }

    public Long getComments() {
        return comments;
    }

    public void setComments(Long comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "BlogVO{" +
                "blog=" + blog +
                ", comments=" + comments +
                ", recommendations=" + recommendations +
                '}';
    }
}
