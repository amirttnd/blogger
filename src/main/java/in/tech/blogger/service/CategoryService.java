package in.tech.blogger.service;

import in.tech.blogger.domain.Category;
import in.tech.blogger.modal.CategoryModal;
import in.tech.blogger.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Boolean save(CategoryModal categoryModal) {
        Category category = new Category();
        category.bind(categoryModal);
        categoryRepository.save(category);
        return categoryRepository.save(category) != null;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
