package in.tech.blogger.service;

import in.tech.blogger.constant.Constants;
import in.tech.blogger.domain.Role;
import in.tech.blogger.modal.UserModel;
import in.tech.blogger.repository.RoleRepository;
import in.tech.blogger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class BootstrapService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    public void initializeRole() {
        if (roleRepository.count() == 0) {
            List<String> authorities = Arrays.asList(Constants.ADMIN_ROLE, Constants.AUTHOR_ROLE, Constants.USER_ROLE);
            for (String authority : authorities) {
                roleRepository.save(new Role(authority));
            }
        }
    }

    public void initializeAdmin() {
        if (userRepository.count() == 0) {
            UserModel userModel = new UserModel();
            userModel.setEmail("mohdamir44@gmail.com");
            userModel.setAuthorities(Arrays.asList(Constants.ADMIN_ROLE, Constants.AUTHOR_ROLE, Constants.USER_ROLE));
            userModel.setEnabled(true);
            userModel.setPassword("igdefault");
            userModel.setFullName("Mohd Amir");
            userService.save(userModel);
        }
    }
}
