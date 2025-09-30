package Deutsche.FindAged.controller;

/**
 * @author Sarav on 30 Sep 2025
 * @project govtech
 * @package Deutsche.FindAged.controller
 * @class UserController
 */

import Deutsche.FindAged.entity.User;
import Deutsche.FindAged.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/older-than-25")
    public ResponseEntity<List<User>> getUsersOlderThan25() {
        return ResponseEntity.ok(userService.getUsersOlderThan25());
    }

    @GetMapping("/older-than")
    public ResponseEntity<List<User>> getUsersOlderThan(@RequestParam int age) {
        return ResponseEntity.ok(userService.getUsersOlderThan(age));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }
}