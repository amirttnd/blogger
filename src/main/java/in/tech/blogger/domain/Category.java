package in.tech.blogger.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.tech.blogger.modal.CategoryModel;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

    String creator;

    @CreatedDate
    Date dateCreated;

    @LastModifiedDate
    Date lastUpdated;

    Short rank;


    public void partialBind(CategoryModel model) {
        if (model != null) {
            if (model.getRank() != null) {
                rank = model.getRank();
            }
        }
    }

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

    public static List<Category> breadcrumb(Category category) {
        List<Category> list = new ArrayList<>();
        Category next = category;
        while (next != null) {
            list.add(next);
            next = next.getParent();
            System.out.println(next);
        }
        Collections.sort(list, (o1, o2) -> {
            return Long.compare(o1.dateCreated.getTime(), o2.dateCreated.getTime());
        });
        return list;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Short getRank() {
        if (rank == null) {
            return 0;
        }
        return rank;
    }

    @JsonIgnore
    public Category getRoot() {
        return Category.breadcrumb(this).get(0);
    }

    public Boolean isParent() {
        return parent == null;
    }

    public void setRank(Short rank) {
        this.rank = rank;
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
                ", creator='" + creator + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
