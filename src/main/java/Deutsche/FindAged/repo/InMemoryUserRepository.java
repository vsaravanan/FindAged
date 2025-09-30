package Deutsche.FindAged.repo;

/**
 * @author Sarav on 30 Sep 2025
 * @project govtech
 * @package Deutsche.FindAged.repo
 * @class InMemoryUserRepository
 */
import Deutsche.FindAged.entity.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    public InMemoryUserRepository() {
        // Initialize with some sample data
        initializeSampleData();
    }

    private void initializeSampleData() {
        save(new User("1", "Alice Johnson", 28, "alice@email.com"));
        save(new User("2", "Bob Smith", 22, "bob@email.com"));
        save(new User("3", "Carol Davis", 35, "carol@email.com"));
        save(new User("4", "David Wilson", 42, "david@email.com"));
        save(new User("5", "Eva Brown", 19, "eva@email.com"));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public void deleteById(String id) {
        users.remove(id);
    }

    @Override
    public List<User> findByAgeGreaterThan(int age) {
        return users.values().stream()
                .filter(user -> user.getAge() > age)
                .toList();
    }
}