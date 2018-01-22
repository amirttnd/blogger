package in.tech.blogger.service;

import in.tech.blogger.domain.Category;
import in.tech.blogger.modal.CategoryModel;
import in.tech.blogger.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Boolean save(CategoryModel categoryModal) {
        Category category = new Category();
        category.bind(categoryModal);
        categoryRepository.save(category);
        return categoryRepository.save(category) != null;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Boolean toggle(Long id) {
        Category category = categoryRepository.findOne(id);
        if (category != null) {
            category.setActive(!category.getActive());
            categoryRepository.save(category);
            return true;
        }
        return false;
    }
}
