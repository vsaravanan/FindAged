package Deutsche.FindAged.service;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sarav on 30 Sep 2025
 * @project govtech
 * @package Deutsche.FindAged.service
 * @class UserServiceTest
 */

import Deutsche.FindAged.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.yml")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testGetUsersOlderThan25() {
        List<User> usersOver25 = userService.getUsersOlderThan25();

        assertNotNull(usersOver25);
        assertFalse(usersOver25.isEmpty());

        // Verify all returned users are actually older than 25
        usersOver25.forEach(user -> assertTrue(user.getAge() > 25));

        // Should contain users with ages: 28, 35, 42, 29
        assertEquals(4, usersOver25.size());
    }

    @Test
    void testGetUsersOlderThan() {
        List<User> usersOver30 = userService.getUsersOlderThan(30);

        assertNotNull(usersOver30);

        // Verify all returned users are actually older than 30
        usersOver30.forEach(user -> assertTrue(user.getAge() > 30));

        // Should contain users with ages: 35, 42
        assertEquals(2, usersOver30.size());
    }

    @Test
    void testGetUsersOlderThanWithNegativeAge() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.getUsersOlderThan(-1));
    }

    @Test
    void testCreateUser() {
        User newUser = new User("John Doe", 27, "john@email.com");
        User savedUser = userService.createUser(newUser);

        assertNotNull(savedUser.getId());
        assertEquals("John Doe", savedUser.getName());
        assertEquals(27, savedUser.getAge());
        assertEquals("john@email.com", savedUser.getEmail());
    }

    @Test
    void testCountUsersOlderThan() {
        long count = userService.countUsersOlderThan(25);
        assertEquals(4, count);
    }
}