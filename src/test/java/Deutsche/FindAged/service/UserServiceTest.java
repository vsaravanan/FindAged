package Deutsche.FindAged.service;

/**
 * @author Sarav on 30 Sep 2025
 * @project govtech
 * @package Deutsche.FindAged.service
 * @class UserServiceTest
 */

import Deutsche.FindAged.entity.User;
import Deutsche.FindAged.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUsersOlderThan25() {
        // Given
        List<User> mockUsers = List.of(
                new User(1L, "Alice", 28, "alice@email.com"),
                new User(2L, "Carol", 35, "carol@email.com")
        );

        when(userRepository.findByAgeGreaterThan(25)).thenReturn(mockUsers);

        // When
        List<User> result = userService.getUsersOlderThan25();

        // Then
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findByAgeGreaterThan(25);
    }

    @Test
    void testGetUsersOlderThanWithNegativeAge() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.getUsersOlderThan(-1));
    }
}