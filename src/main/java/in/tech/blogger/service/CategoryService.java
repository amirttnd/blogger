package in.tech.blogger.service;

import in.tech.blogger.domain.Category;
import in.tech.blogger.modal.CategoryModel;
import in.tech.blogger.repository.CategoryRepository;
import in.tech.blogger.vo.CategoryTreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Boolean save(CategoryModel categoryModel) {
        Category category = new Category();
        return true;
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
//
//    CategoryTreeVO makeSubcategories(Category category) {
//        CategoryTreeVO categoryTreeVO = new CategoryTreeVO();
//        if (!categoryTreeVO.getSubCategories().isEmpty()) {
//            for (Category subCategory : categoryTreeVO.getSubCategories()) {
//                makeSubcategories(subCategory);
//            }
//        }
//        return categoryTreeVO;
//    }
//
//    CategoryTreeVO makeTreeNode(Category category) {
//        CategoryTreeVO categoryTreeVO = new CategoryTreeVO();
//        List<Category> subCategories = new ArrayList<>();
//        subCategories = categoryRepository.findAllByParent(category);
//        categoryTreeVO.setCategory(category);
//        for (Category subCategory : subCategories) {
//            categoryTreeVO.addToSubcategories(subCategory);
//        }
//        return categoryTreeVO;
//    }
}
