package in.tech.blogger.repository;


import in.tech.blogger.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select * from category c order by c.id desc", nativeQuery = true)
    List<Category> findAll();
}
