package Deutsche.FindAged.service;

import Deutsche.FindAged.entity.User;

import java.util.List;

/**
 * @author Sarav on 30 Sep 2025
 * @project govtech
 * @package Deutsche.FindAged.service
 * @class UserService
 */
public interface UserService {
    List<User> getUsersOlderThan25();
    List<User> getUsersOlderThan(int age);
    List<User> getAllUsers();
    User createUser(User user);
}