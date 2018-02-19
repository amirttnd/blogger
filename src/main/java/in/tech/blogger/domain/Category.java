package in.tech.blogger.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.tech.blogger.vo.CategoryVO;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "category")
public class Category implements Persistable<String> {

    @Id
    String id;

    @Field
    @NotEmpty
    @Indexed
    String name;

    Boolean active;

    @NotNull
    Integer level;

    @DBRef
    Category parent;

    @Indexed
    String friendlyUrl;

    @CreatedDate
    Date dateCreated;

    @LastModifiedDate
    Date lastUpdated;

    @Override
    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Boolean getActive() {
        if (active == null) {
            return false;
        }
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getFriendlyUrl() {
        return friendlyUrl;
    }

    public void setFriendlyUrl(String friendlyUrl) {
        this.friendlyUrl = friendlyUrl;
    }

    @JsonIgnore
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @JsonIgnore
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public CategoryVO toCategoryVO() {
        return new CategoryVO(this);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", level=" + level +
                ", parent=" + parent +
                ", friendlyUrl='" + friendlyUrl + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
