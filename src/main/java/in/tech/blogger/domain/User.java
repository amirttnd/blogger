package in.tech.blogger.domain;

import in.tech.blogger.modal.UserModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Document(collection = "user")
public class User {


    @Id
    String id;

    String fullName;

    String email;

    Boolean enabled = true;

    String password;

    @DBRef
    List<Role> authorities;

    public void bind(UserModel userModel) {
        if (userModel != null) {
            this.email = userModel.getEmail();
            this.fullName = userModel.getFullName();
            this.enabled = userModel.getEnabled();
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
