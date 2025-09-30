package Deutsche.FindAged.config;

import Deutsche.FindAged.repo.InMemoryUserRepository;
import Deutsche.FindAged.repo.UserRepository;
import Deutsche.FindAged.service.UserService;
import Deutsche.FindAged.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sarav on 30 Sep 2025
 * @project govtech
 * @package Deutsche.FindAged.config
 * @class AppConfig
 */
@Configuration
public class AppConfig {

    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }
}
