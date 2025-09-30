package Deutsche.FindAged.service;

import Deutsche.FindAged.entity.User;
import Deutsche.FindAged.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sarav on 30 Sep 2025
 * @project govtech
 * @package Deutsche.FindAged.service
 * @class UserServiceImpl
 */


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsersOlderThan(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        return userRepository.findByAgeGreaterThan(age);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        if (user.getAge() < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        return userRepository.save(user);
    }

    // Specific method for users older than 25
    public List<User> getUsersOlderThan25() {
        return getUsersOlderThan(25);
    }
}
