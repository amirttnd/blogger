package in.tech.blogger.repository;

import in.tech.blogger.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

    String findById(String id);

    String findByAuthority(String authority);
}
