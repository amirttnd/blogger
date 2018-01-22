package in.tech.blogger.modal;

public class CategoryModel {
    String name;
    Boolean active;

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
