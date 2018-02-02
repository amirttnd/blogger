package in.tech.blogger.vo;

import in.tech.blogger.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryTreeVO {

    Category category = new Category();
    List<Category> subCategories = new ArrayList<Category>();

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public void addToSubcategories(Category category) {
        subCategories.add(category);
    }

}
