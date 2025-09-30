package Deutsche.FindAged;

import Deutsche.FindAged.entity.User;
import Deutsche.FindAged.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FindAgedApplication  implements CommandLineRunner {

    @Autowired
    private UserService userService;

	public static void main(String[] args) {

        SpringApplication.run(FindAgedApplication.class, args);
	}


    @Override
    public void run(String... args) {
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
