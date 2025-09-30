package Deutsche.FindAged.repo;

import Deutsche.FindAged.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Sarav on 30 Sep 2025
 * @project govtech
 * @package Deutsche.FindAged.repo
 * @class UserRepository
 */


public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(String id);
    User save(User user);
    void deleteById(String id);
    List<User> findByAgeGreaterThan(int age);
}