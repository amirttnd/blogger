package in.tech.blogger.repository;

import in.tech.blogger.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    Blog findById(Long id);
}
