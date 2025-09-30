package Deutsche.FindAged.entity;

/**
 * @author Sarav on 30 Sep 2025
 * @project govtech
 * @package Deutsche.FindAged.entity
 * @class User
 */

public class User {
    private final String id;
    private final String name;
    private final int age;
    private final String email;

    public User(String id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "User{id='" + id + "', name='" + name + "', age=" + age + ", email='" + email + "'}";
    }
}