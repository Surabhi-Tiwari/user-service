package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.entity.User;
import com.example.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    // Create
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Get all users
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.getAllUser();
        return ResponseEntity.ok(list);
    }

    // Get by id
    @GetMapping("/get/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userService.getByIdUser(userId);
        return ResponseEntity.ok(user);
    }

    // Delete
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userService.deleteByUserId(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    // Update
    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) {
        User updated = userService.updateUser(userId, user);
        return ResponseEntity.ok(updated);
    }
}
