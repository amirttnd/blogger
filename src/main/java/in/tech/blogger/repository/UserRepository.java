package in.tech.blogger.repository;

import in.tech.blogger.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findById(String id);

    User findByEmail(String email);

    List<User> findAllIsEnabled(Boolean enabled);

    Integer countByEmail(String email);

}
