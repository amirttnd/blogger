package in.tech.blogger.domain;

import in.tech.blogger.modal.CommentModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment {

    @Id
    String id;

    String email;

    String fullName;

    String referenceId;

    String comment;

    String websiteURL;

    String parentId;

    public Comment() {

    }

    public Comment(CommentModel model) {
        bind(model);
    }

    public void bind(CommentModel model) {
        if (model != null) {
            this.email = model.getEmail();
            this.fullName = model.getFullName();
            this.referenceId = model.getReferenceId();
            this.comment = model.getComment();
            this.parentId = model.getParentId();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
