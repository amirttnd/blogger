package in.tech.blogger.service;


import in.tech.blogger.domain.Blog;
import in.tech.blogger.domain.Category;
import in.tech.blogger.modal.BlogModel;
import in.tech.blogger.repository.BlogRepository;
import in.tech.blogger.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Blog save(BlogModel blogModel) {
        if (blogModel.getShortHeading() != null) {
            Category category = categoryRepository.findById(blogModel.getCategoryId());
            List<Category> relatedCategories = categoryRepository.findAllByIdInList(blogModel.getRelatedCategories());
            System.out.println(relatedCategories);
            Optional<Blog> blogOptional = Optional.ofNullable(blogRepository.findById(blogModel.getId()));
            Blog blog = blogOptional.orElse(new Blog());
            blog.bind(blogModel);
            blog.setCategory(category);
            blog.setRelatedCategories(relatedCategories);
            blogRepository.save(blog);
        }
        return null;
    }

    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    public Blog findById(String id) {
        return blogRepository.findById(id);
    }

    public List<Blog> findAllByCategory(String id) {
        Category category = categoryRepository.findById(id);
        return blogRepository.findAllByCategory(category);
    }
}
