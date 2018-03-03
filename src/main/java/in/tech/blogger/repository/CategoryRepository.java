package in.tech.blogger.repository;


import in.tech.blogger.domain.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {

    List<Category> findAll(Sort sort);

    @Query(value = "{'parent':null,active:?0}")
    List<Category> getCategoryParents(boolean active);

    @Query(value = "{'parent':null,active:?0}")
    List<Category> getCategoryParents(boolean active, Sort sort);

    List<Category> findAllByParentAndActive(Category category, boolean active);

    List<Category> findAllByParentAndActive(Category category, boolean active, Sort sort);

    Category findById(String id);

    Category findByFriendlyUrl(String friendlyUrl);

    @Query("{'_id':{$in:?0}}")
    List<Category> findAllByIdInList(List<String> id);
}
