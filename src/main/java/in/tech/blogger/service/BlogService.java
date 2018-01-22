package in.tech.blogger.service;


import in.tech.blogger.domain.Blog;
import in.tech.blogger.modal.BlogModel;
import in.tech.blogger.repository.BlogRepository;
import in.tech.blogger.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Boolean save(BlogModel blogModel) {
        Optional<Blog> blogOptional = Optional.ofNullable(blogRepository.findById(blogModel.getId()));
        Blog blog = blogOptional.orElse(new Blog());
        blog.bind(blogModel);
        blog.setCategory(categoryRepository.findOne(blogModel.getCategoryId()));
        return blogRepository.save(blog) != null;
    }

    public List<Blog> findAll() {
        return blogRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
    }

    public Blog findById(Long id) {
        if (id != null) {
            return blogRepository.findById(id);
        }
        return null;
    }
}
