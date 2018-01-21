package in.tech.blogger.domain;

import in.tech.blogger.modal.CategoryModal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    @NotNull
    String name;

    @Column
    Boolean active = false;

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

    public void bind(CategoryModal categoryModal) {
        this.setName(categoryModal.getName());
        this.setActive(categoryModal.getActive());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
