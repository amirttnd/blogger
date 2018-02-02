package in.tech.blogger.modal;

public class CategoryModel {
    String id;

    String name;

    Boolean active;

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "CategoryModal{" +
                "name='" + name + '\'' +
                ", active=" + active +
                '}';
    }
}
