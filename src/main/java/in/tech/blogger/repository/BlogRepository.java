package in.tech.blogger.repository;

import in.tech.blogger.domain.Blog;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {

    Blog findById(String id);

    List<Blog> findAll(Sort sort);
}
