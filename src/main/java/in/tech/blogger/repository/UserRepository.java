package in.tech.blogger.repository;

import in.tech.blogger.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findById(String id);

    User findByEmail(String email);

    Integer countByEmail(String email);

}
