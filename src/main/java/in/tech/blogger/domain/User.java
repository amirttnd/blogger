package in.tech.blogger.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.tech.blogger.modal.UserModel;
import in.tech.blogger.util.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Document(collection = "user")
@CompoundIndex(def = "{email:'text',fullName:'text'}", name = "search_index")
public class User {


    @Id
    String id;

    String fullName;

    String email;

    Boolean enabled = true;

    String password;

    @DBRef
    List<Role> authorities;

    @CreatedDate
    Date dateCreated;

    @LastModifiedDate
    Date lastUpdated;

    public void bind(UserModel userModel) {
        if (userModel != null) {
            this.email = userModel.getEmail();
            this.fullName = userModel.getFullName();
            this.enabled = userModel.getEnabled();
            if (userModel.getPassword() != null) {
                this.password = BeanUtils.getPasswordEncoder().encode(userModel.getPassword());
            }
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addToAuthorities(Role role) {
        if (role != null) {
            authorities = Optional.ofNullable(authorities).orElse(new ArrayList<>());
            authorities.add(role);
        }
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

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
