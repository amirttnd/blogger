package in.tech.blogger.repository;


import in.tech.blogger.domain.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {

    List<Category> findAll(Sort sort);

    @Query(value = "{'parent':null}")
    List<Category> getCategoryParents();

    List<Category> findAllByParent(Category category);

    Category findById(String id);
}
