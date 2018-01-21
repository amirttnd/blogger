package in.tech.blogger.domain;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    @NotNull
    String title;

    @Column
    @Type(type = "text")
    String content;

    @Column(unique = true, nullable = false)
    String friendlyUrl;

    @Column
    Boolean active;

    @OneToOne
    User creator;

    @OneToOne
    @NotNull
    Category category;

    @Column
    Date dateCreated;

    @Column
    Date lastUpdated;

    @PrePersist
    public void beforeInsert() {
        dateCreated = new Date();
        lastUpdated = new Date();
    }

    @PreUpdate
    public void beforeUpdate() {
        lastUpdated = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFriendlyUrl() {
        return friendlyUrl;
    }

    public void setFriendlyUrl(String friendlyUrl) {
        this.friendlyUrl = friendlyUrl;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
