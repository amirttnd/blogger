package in.tech.blogger.service;

import in.tech.blogger.domain.Blog;
import in.tech.blogger.domain.Category;
import in.tech.blogger.modal.CategoryModel;
import in.tech.blogger.query.BlogQuery;
import in.tech.blogger.repository.BlogRepository;
import in.tech.blogger.repository.CategoryRepository;
import in.tech.blogger.util.Utils;
import in.tech.blogger.vo.CategoryTreeVO;
import in.tech.blogger.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    CacheService cacheService;

    @Autowired
    MongoTemplate mongoTemplate;

    public Category save(CategoryModel categoryModel) {
        if (categoryModel.getName() != null) {
            Category category = new Category();
            category.setActive(true);
            category.setParent(null);
            category.setName(categoryModel.getName());
            category.setLevel(1);
            category.setFriendlyUrl(Utils.toFriendlyURL(category.getName()));
            cacheService.expireCategoryTree();
            return categoryRepository.save(category);
        }
        return null;
    }

    public Category update(CategoryModel categoryModel) {
        Category category = categoryRepository.findById(categoryModel.getId());
        if (categoryModel.getName() != null) {
            category.setName(categoryModel.getName());
            cacheService.expireCategoryTree();
            return categoryRepository.save(category);
        }
        return null;
    }

    public Category addChild(CategoryModel categoryModel) {
        Category parent = categoryRepository.findById(categoryModel.getId());
        if (parent != null) {
            Category child = new Category();
            child.setActive(true);
            child.setParent(parent);
            child.setName(categoryModel.getName());
            child.setLevel(parent.getLevel() + 1);
            child.setFriendlyUrl(Utils.toFriendlyURL(parent.getName() + " " + child.getName()));
            cacheService.expireCategoryTree();
            return categoryRepository.save(child);
        }
        return null;
    }

    public Category toggle(String id) {
        Category category = categoryRepository.findById(id);
        if (category != null) {
            category.setActive(!category.getActive());
            category = categoryRepository.save(category);
            cacheService.expireCategoryTree();
        }
        return category;
    }

    public Boolean delete(String id) {
        Category category = categoryRepository.findById(id);
        if (category != null) {
            categoryRepository.delete(category);
            cacheService.expireCategoryTree();
            return true;
        }
        return false;
    }

    public List<Category> getCategoryParents(boolean active) {
        return categoryRepository.getCategoryParents(active);
    }

    public List<CategoryTreeVO> tree() {
        List<CategoryTreeVO> treeVOs = new ArrayList<>();
        CategoryTreeVO categoryTreeVO = null;
        List<Category> parents = getCategoryParents(true);
        List<Category> subCategories = new ArrayList<>();
        for (Category parent : parents) {
            categoryTreeVO = new CategoryTreeVO();
            categoryTreeVO.setCategory(parent);
            subCategories = categoryRepository.findAllByParentAndActive(parent, true);
            for (Category subCategory : subCategories) {
                categoryTreeVO.addToSubcategories(subCategory);
            }
            treeVOs.add(categoryTreeVO);
        }
        return treeVOs;
    }

    public CategoryVO detail(String friendlyUrl, Integer page) {
        CategoryVO categoryVO = new CategoryVO();

        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setCategoryQ(friendlyUrl);
        blogQuery.setPage(page);

        if (friendlyUrl != null) {
            Category category = categoryRepository.findByFriendlyUrl(friendlyUrl);
            categoryVO.bind(category);

            List<Blog> blogs = mongoTemplate.find(blogQuery.build(), Blog.class);
            categoryVO.setBlogs(blogs);

            categoryVO.setTotalBlogs(mongoTemplate.count(blogQuery.build(), Blog.class));
        }
        return categoryVO;
    }

}
