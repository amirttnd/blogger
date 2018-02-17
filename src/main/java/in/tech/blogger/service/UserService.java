package in.tech.blogger.service;

import in.tech.blogger.constant.Constants;
import in.tech.blogger.domain.Role;
import in.tech.blogger.domain.User;
import in.tech.blogger.modal.UserModel;
import in.tech.blogger.repository.RoleRepository;
import in.tech.blogger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public User save(UserModel userModel) {

        Optional<User> userOptional = Optional.ofNullable(userRepository.findById(userModel.getId()));
        User user = userOptional.orElse(new User());
        user.bind(userModel);
        user.setPassword(userModel.getPassword());
        List<Role> authorities = roleRepository.findAllByAuthorityIn(userModel.getAuthorities());
        if (authorities != null && authorities.size() == 0) {
            user.addToAuthorities(roleRepository.findByAuthority(Constants.USER_ROLE));
        } else {
            user.setAuthorities(authorities);
        }
        return userRepository.save(user);
    }
}
