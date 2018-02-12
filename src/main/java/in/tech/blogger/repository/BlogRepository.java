package in.tech.blogger.repository;

import in.tech.blogger.domain.Blog;
import in.tech.blogger.domain.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {

    Blog findById(String id);

    List<Blog> findAll(Sort sort);

    List<Blog> findAllByCategory(Category category);

    Blog findByFriendlyUrl(String friendlyUrl);

    List<Blog> findAllByCategoryAndIsRecommended(Category category, Boolean isRecommended);
}
