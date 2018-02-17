package in.tech.blogger.repository;

import in.tech.blogger.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

    Role findById(String id);

    Role findByAuthority(String authority);

    List<Role> findAllByAuthorityIn(List<String> authority);
}
