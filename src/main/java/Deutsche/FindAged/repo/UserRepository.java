package Deutsche.FindAged.repo;

import Deutsche.FindAged.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Sarav on 30 Sep 2025
 * @project govtech
 * @package Deutsche.FindAged.repo
 * @class UserRepository
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

    // Method 1: Derived query method
    List<User> findByAgeGreaterThan(int age);

    // Method 2: Custom JPQL query
    @Query("SELECT u FROM User u WHERE u.age > :age ORDER BY u.age DESC")
    List<User> findUsersOlderThan(@Param("age") int age);

    // Method 3: Native SQL query
    @Query(value = "SELECT * FROM users WHERE age > :age", nativeQuery = true)
    List<User> findUsersOlderThanNative(@Param("age") int age);

    // Additional useful methods
    List<User> findByAgeBetween(int startAge, int endAge);
    boolean existsByEmail(String email);

    List<User> findByAgeGreaterThanOrderByNameAsc(int age);
}