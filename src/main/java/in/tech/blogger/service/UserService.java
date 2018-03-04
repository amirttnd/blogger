package in.tech.blogger.service;

import in.tech.blogger.constant.Constants;
import in.tech.blogger.domain.Role;
import in.tech.blogger.domain.User;
import in.tech.blogger.modal.UserModel;
import in.tech.blogger.query.UserQuery;
import in.tech.blogger.repository.RoleRepository;
import in.tech.blogger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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

    @Autowired
    MongoTemplate mongoTemplate;

    public User save(UserModel userModel) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(userModel.getEmail()));
        User user = userOptional.orElse(new User());
        user.bind(userModel);
        List<Role> authorities = roleRepository.findAllByAuthorityIn(userModel.getAuthorities());
        user.setAuthorities(null);
        if (authorities != null && authorities.size() == 0) {
            user.addToAuthorities(roleRepository.findByAuthority(Constants.USER_ROLE));
        } else {
            user.setAuthorities(authorities);
        }
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Boolean delete(String id) {
        if (id != null) {
            userRepository.delete(id);
            return true;
        }
        return false;
    }

    public User findBy(String email) {
        if (email != null) {
            return userRepository.findByEmail(email);
        }
        return null;
    }

    public List<User> findAllIsEnabled(Boolean enabled) {
        return userRepository.findAllIsEnabled(enabled);
    }

    public List<User> search(UserQuery userQuery) {
        return mongoTemplate.find(userQuery.build(), User.class);
    }

    public long count(UserQuery userQuery) {
        return mongoTemplate.count(userQuery.build(), User.class);
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
