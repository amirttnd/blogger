package in.tech.blogger.service;

import in.tech.blogger.domain.Category;
import in.tech.blogger.modal.CategoryModel;
import in.tech.blogger.repository.CategoryRepository;
import in.tech.blogger.vo.CategoryTreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category save(CategoryModel categoryModel) {
        Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findById(categoryModel.getId()));
        if (categoryModel.getName() != null) {
            Category category = categoryOptional.orElse(new Category());
            category.setActive(true);
            category.setParent(null);
            category.setName(categoryModel.getName());
            category.setLevel(1);
            return categoryRepository.save(category);
        }
        return null;
    }

    public Category addChild(CategoryModel categoryModel) {
        System.out.println(categoryModel.toString());
        Category parent = categoryRepository.findById(categoryModel.getId());
        if (parent != null) {
            Category child = new Category();
            child.setActive(true);
            child.setParent(parent);
            child.setName(categoryModel.getName());
            child.setLevel(parent.getLevel() + 1);
            return categoryRepository.save(child);
        }
        return null;
    }

    public Category toggle(String id) {
        Category category = categoryRepository.findById(id);
        if (category != null) {
            category.setActive(!category.getActive());
            return categoryRepository.save(category);
        }
        return category;
    }

    public Boolean delete(String id) {
        Category category = categoryRepository.findById(id);
        if (category != null) {
            categoryRepository.delete(category);
            return true;
        }
        return false;
    }

    public List<Category> getCategoryParents() {
        return categoryRepository.getCategoryParents();
    }

    public List<CategoryTreeVO> tree() {
        List<CategoryTreeVO> treeVOs = new ArrayList<>();
        CategoryTreeVO categoryTreeVO = null;
        List<Category> parents = getCategoryParents();
        List<Category> subCategories = new ArrayList<>();
        for (Category parent : parents) {
            categoryTreeVO = new CategoryTreeVO();
            categoryTreeVO.setCategory(parent);
            subCategories = categoryRepository.findAllByParent(parent);
            for (Category subCategory : subCategories) {
                categoryTreeVO.addToSubcategories(subCategory);
            }
            treeVOs.add(categoryTreeVO);
        }
        return treeVOs;
    }

}
