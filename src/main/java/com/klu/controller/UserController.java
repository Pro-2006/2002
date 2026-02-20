package com.klu.controller;

import com.klu.entity.User;
import com.klu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ============ BASIC CRUD OPERATIONS ============

    // GET all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // GET user by ID (PATH VARIABLE)
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // POST create user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    // PUT update user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return ResponseEntity.ok(userService.updateUser(user));
    }

    // DELETE user by ID (PATH VARIABLE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    // ============ PATH VARIABLE SEARCH & FILTER ============

    // GET user by name (PATH VARIABLE)
    @GetMapping("/name/{name}")
    public ResponseEntity<List<User>> getUsersByName(@PathVariable String name) {
        return ResponseEntity.ok(userService.getUsersByName(name));
    }

    // GET users by email (PATH VARIABLE)
    @GetMapping("/email/{email}")
    public ResponseEntity<List<User>> getUsersByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.searchUserByEmail(email));
    }

    // GET users by status (PATH VARIABLE) - active/inactive
    @GetMapping("/status/{status}")
    public ResponseEntity<List<User>> getUsersByStatus(@PathVariable String status) {
        Boolean isActive = "active".equalsIgnoreCase(status);
        return ResponseEntity.ok(userService.findByNameAndActive("", isActive));
    }

    // GET all active users
    @GetMapping("/filter/active-list")
    public ResponseEntity<List<User>> getActiveUsers() {
        return ResponseEntity.ok(userService.getActiveUsers());
    }

    // GET count of active users (PATH VARIABLE for info)
    @GetMapping("/count/active-users")
    public ResponseEntity<Long> countActiveUsers() {
        return ResponseEntity.ok(userService.countActiveUsers());
    }

    // ============ ACTION ENDPOINTS WITH PATH VARIABLES ============

    // PATCH activate user (PATH VARIABLE)
    @PatchMapping("/{id}/activate")
    public ResponseEntity<User> activateUser(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            User u = user.get();
            u.setIsActive(true);
            return ResponseEntity.ok(userService.updateUser(u));
        }
        return ResponseEntity.notFound().build();
    }

    // PATCH deactivate user (PATH VARIABLE)
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<User> deactivateUser(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            User u = user.get();
            u.setIsActive(false);
            return ResponseEntity.ok(userService.updateUser(u));
        }
        return ResponseEntity.notFound().build();
    }

    // UPDATE name by ID (PATH VARIABLES)
    @PatchMapping("/{id}/name/{newName}")
    public ResponseEntity<User> updateUserName(@PathVariable Long id, @PathVariable String newName) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            User u = user.get();
            u.setName(newName);
            return ResponseEntity.ok(userService.updateUser(u));
        }
        return ResponseEntity.notFound().build();
    }

    // UPDATE email by ID (PATH VARIABLES)
    @PatchMapping("/{id}/email/{newEmail}")
    public ResponseEntity<User> updateUserEmail(@PathVariable Long id, @PathVariable String newEmail) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            User u = user.get();
            u.setEmail(newEmail);
            return ResponseEntity.ok(userService.updateUser(u));
        }
        return ResponseEntity.notFound().build();
    }

    // UPDATE phone by ID (PATH VARIABLES)
    @PatchMapping("/{id}/phone/{newPhone}")
    public ResponseEntity<User> updateUserPhone(@PathVariable Long id, @PathVariable String newPhone) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            User u = user.get();
            u.setPhone(newPhone);
            return ResponseEntity.ok(userService.updateUser(u));
        }
        return ResponseEntity.notFound().build();
    }

    // ============ SEARCH ENDPOINT (QUERY PARAM) ============

    // Search by email pattern (QUERY PARAM)
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.searchUserByEmail(email));
    }
}

