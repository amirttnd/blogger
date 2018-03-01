package in.tech.blogger.service;

import in.tech.blogger.domain.Contact;
import in.tech.blogger.modal.ContactModel;
import in.tech.blogger.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public Page<Contact> findAll(Pageable pageable) {
        return contactRepository.findAll(pageable);
    }

    public Contact save(ContactModel contactModel) {
        Optional<Contact> contactOptional = Optional.ofNullable(contactRepository.findById(contactModel.getId()));
        Contact contact = contactOptional.orElse(new Contact());
        contact.bind(contactModel);
        return contactRepository.save(contact);
    }
}
