package in.tech.blogger.service;

import in.tech.blogger.domain.Role;
import in.tech.blogger.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public List<Role> findAllByAuthorityIn(List<String> authorities) {
        return roleRepository.findAllByAuthorityIn(authorities);
    }

}
