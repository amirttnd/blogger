package in.tech.blogger.util;

import in.tech.blogger.aware.ApplicationContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BeanUtils {
    public static BCryptPasswordEncoder getPasswordEncoder() {
        return ApplicationContextHolder.getBean("passwordEncoder", BCryptPasswordEncoder.class);
    }
}
