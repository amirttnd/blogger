package in.tech.blogger.service;

import in.tech.blogger.vo.CategoryTreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheService {

    @Autowired
    CategoryService categoryService;

    @Cacheable(cacheNames = "categoryTree", sync = true)
    public List<CategoryTreeVO> categoryTree() {
        System.out.println(">>>>>>>>>>>>> Populating category tree <<<<<<<<<<<<<<<");
        return categoryService.tree();
    }

    @CacheEvict(cacheNames = "categoryTree")
    public void expireCategoryTree() {

    }
}
