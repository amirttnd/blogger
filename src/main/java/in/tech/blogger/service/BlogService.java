package in.tech.blogger.service;


import in.tech.blogger.domain.Blog;
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

    public Boolean save(BlogModel blogModal) {
        Optional<Blog> blogOptional = Optional.ofNullable(blogRepository.findById(blogModal.getId()));
        Blog blog = blogOptional.orElse(new Blog());
        blog.bind(blogModal);
        blog.setCategory(categoryRepository.findOne(blogModal.getCategoryId()));
        return blogRepository.save(blog) != null;
    }

    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    public Blog findById(Long id) {
        if (id != null) {
            return blogRepository.findById(id);
        }
        return null;
    }
}
