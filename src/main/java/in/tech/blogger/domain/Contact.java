package in.tech.blogger.domain;

import in.tech.blogger.modal.ContactModel;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "contact")
public class Contact {

    @Id
    String id;

    String fullName;
    String email;
    String query;

    @CreatedDate
    Date dateCreated;

    @LastModifiedDate
    Date lastUpdated;

    public Contact() {

    }

    public Contact(ContactModel model) {
        bind(model);
    }

    public void bind(ContactModel model){
        if (model != null) {
            this.fullName = model.getFullName();
            this.email = model.getEmail();
            this.query = model.getQuery();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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
