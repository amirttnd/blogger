package in.tech.blogger.repository;

import in.tech.blogger.domain.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContactRepository extends MongoRepository<Contact, String> {

    Contact findById(String id);

    List<Contact> findAll();
//
    Page<Contact> findAll(Pageable pageable);
}
