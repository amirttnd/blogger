package in.tech.blogger.vo;

import in.tech.blogger.domain.Blog;

import java.util.List;

public class BlogVO {
    Blog blog;
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

    @Override
    public String toString() {
        return "BlogVO{" +
                "blog=" + blog +
                ", recommendations=" + recommendations +
                '}';
    }
}
