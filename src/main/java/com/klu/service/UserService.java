package com.klu.service;

import com.klu.entity.User;
import com.klu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Get user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Get users by name
    public List<User> getUsersByName(String name) {
        return userRepository.findByUserName(name);
    }

    // Search users by email
    public List<User> searchUserByEmail(String email) {
        return userRepository.searchByEmail(email);
    }

    // Get all active users
    public List<User> getActiveUsers() {
        return userRepository.findAllActiveUsers();
    }

    // Get all inactive users
    public List<User> getInactiveUsers() {
        return userRepository.findByIsActive(false);
    }

    // Find by name and active status
    public List<User> findByNameAndActive(String name, Boolean active) {
        if (name == null || name.isEmpty()) {
            return userRepository.findByIsActive(active);
        }
        return userRepository.findByNameAndActive(name, active);
    }

    // Count active users
    public long countActiveUsers() {
        return userRepository.countActiveUsers();
    }

    // Update user
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Delete user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

