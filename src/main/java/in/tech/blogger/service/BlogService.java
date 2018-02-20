package in.tech.blogger.service;


import in.tech.blogger.domain.Blog;
import in.tech.blogger.domain.Category;
import in.tech.blogger.modal.BlogModel;
import in.tech.blogger.query.BlogQuery;
import in.tech.blogger.repository.BlogRepository;
import in.tech.blogger.repository.CategoryRepository;
import in.tech.blogger.repository.UserRepository;
import in.tech.blogger.vo.BlogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CommentService commentService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public Blog save(BlogModel blogModel) {
        if (blogModel.getShortHeading() != null) {
            List<String> inCategories = new ArrayList<>();
            Category category = categoryRepository.findById(blogModel.getCategoryId());
            List<Category> relatedCategories = categoryRepository.findAllByIdInList(blogModel.getRelatedCategories());
            Optional<Blog> blogOptional = Optional.ofNullable(blogRepository.findById(blogModel.getId()));
            Blog blog = blogOptional.orElse(new Blog());
            blog.bind(blogModel);
            blog.setCategory(category);
            blog.setUser(userRepository.findByEmail(blogModel.getUsername()));

            if (category.getParent() != null) {
                inCategories.add(category.getParent().getFriendlyUrl());
            }
            inCategories.add(category.getFriendlyUrl());
            blog.setInCategories(inCategories);
            blog.setRelatedCategories(relatedCategories);
            blogRepository.save(blog);
        }
        return null;
    }

    public Blog findById(String id) {
        return blogRepository.findById(id);
    }

    public List<BlogVO> findAllByCategory(Category category) {
        return findAllByCategory(category, 20);
    }

    public List<BlogVO> findAllByCategory(Category category, Integer max) {
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setOnlyPublished(true);
        blogQuery.setFieldsToExclude(Arrays.asList("content", "briefIntroduction", "relatedCategories"));
        blogQuery.setCategoryId(category.getId());
        blogQuery.setMax(max);
        return search(blogQuery);
    }

    public BlogVO findAndIncView(String friendlyUrl) {
        BlogVO blogVO = new BlogVO();
        Blog blog = blogRepository.findByFriendlyUrl(friendlyUrl);
        if (blog != null) {
            blog.incViews();
            blogRepository.save(blog);

            blogVO.setBlog(blog);
            blogVO.setComments(commentService.countByReferenceId(blog.getId()));
            blogVO.setRecommendations(blogRepository.findAllByCategoryAndIsRecommended(blog.getCategory(), true));
        }
        return blogVO;
    }

    public List<BlogVO> search(BlogQuery blogQuery) {
        List<Blog> blogs = mongoTemplate.find(blogQuery.build(), Blog.class);
        List<BlogVO> blogVOs = new ArrayList<BlogVO>();
        BlogVO blogVO;
        for (Blog blog : blogs) {
            blogVO = new BlogVO();
            blogVO.setBlog(blog);
            blogVO.setComments(commentService.countByReferenceId(blog.getId()));
            blogVOs.add(blogVO);
        }
        return blogVOs;
    }

    public Blog toggleRecommendation(String id) {
        Blog blog = blogRepository.findById(id);
        if (blog != null) {
            blog.setIsRecommended(!blog.getIsRecommended());
            return blogRepository.save(blog);
        }
        return null;
    }

}
