package Deutsche.FindAged.data;

/**
 * @author Sarav on 30 Sep 2025
 * @project govtech
 * @package Deutsche.FindAged.data
 * @class DataInitializer
 */

import Deutsche.FindAged.entity.User;
import Deutsche.FindAged.repo.UserRepository;
import Deutsche.FindAged.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer  implements CommandLineRunner  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;


    public void run(String... args) throws Exception {
        // Initialize with sample data
        if (userRepository.count() == 0) {
            userRepository.save(new User("Alice Johnson", 28, "alice@email.com"));
            userRepository.save(new User("Bob Smith", 22, "bob@email.com"));
            userRepository.save(new User("Carol Davis", 35, "carol@email.com"));
            userRepository.save(new User("David Wilson", 42, "david@email.com"));
            userRepository.save(new User("Eva Brown", 19, "eva@email.com"));
            userRepository.save(new User("Frank Miller", 29, "frank@email.com"));

            System.out.println("Sample data initialized!");
        }

        // Get all users older than 25
        List<User> usersOver25 = userService.getUsersOlderThan25();

        System.out.println("Users older than 25:");
        usersOver25.forEach(System.out::println);

        // Alternative: using the generic method
        List<User> usersOver30 = userService.getUsersOlderThan(30);
        System.out.println("\nUsers older than 30:");
        usersOver30.forEach(System.out::println);
    }
}