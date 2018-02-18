package in.tech.blogger.service;

import in.tech.blogger.constant.Constants;
import in.tech.blogger.domain.Role;
import in.tech.blogger.domain.User;
import in.tech.blogger.modal.UserModel;
import in.tech.blogger.repository.RoleRepository;
import in.tech.blogger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public User save(UserModel userModel) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findById(userModel.getId()));
        User user = userOptional.orElse(new User());
        user.bind(userModel);
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        List<Role> authorities = roleRepository.findAllByAuthorityIn(userModel.getAuthorities());
        if (authorities != null && authorities.size() == 0) {
            user.addToAuthorities(roleRepository.findByAuthority(Constants.USER_ROLE));
        } else {
            user.setAuthorities(authorities);
        }
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username " + username);
        }
        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
        for (Role role : user.getAuthorities()) {
            auths.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getEnabled(),
                user.getEnabled(),
                user.getEnabled(),
                user.getEnabled(),
                auths
        );
    }
}
