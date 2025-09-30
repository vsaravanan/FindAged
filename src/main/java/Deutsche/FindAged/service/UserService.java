package Deutsche.FindAged.service;

import Deutsche.FindAged.entity.User;
import Deutsche.FindAged.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class UserService {


    @Autowired
    UserRepository userRepository;

    /**
     * Get all users older than 25
     */
    public List<User> getUsersOlderThan25() {
        return userRepository.findByAgeGreaterThan(25);
    }

    /**
     * Get all users older than specified age
     */
    public List<User> getUsersOlderThan(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        return userRepository.findByAgeGreaterThan(age);
    }

    /**
     * Get all users older than specified age, ordered by name
     */
    public List<User> getUsersOlderThanOrderByName(int age) {
        return userRepository.findByAgeGreaterThanOrderByNameAsc(age);
    }

    /**
     * Get all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Create a new user
     */
    @Transactional
    public User createUser(User user) {
        if (user.getAge() < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + user.getEmail());
        }

        return userRepository.save(user);
    }

    /**
     * Get user by ID
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    /**
     * Update user
     */
    @Transactional
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setName(userDetails.getName());
        user.setAge(userDetails.getAge());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    /**
     * Delete user by ID
     */
    @Transactional
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    /**
     * Get users count older than specified age
     */
    public long countUsersOlderThan(int age) {
        return getUsersOlderThan(age).size();
    }

    /**
     * Check if any users exist older than specified age
     */
    public boolean existsUsersOlderThan(int age) {
        return !getUsersOlderThan(age).isEmpty();
    }
}