package com.klu.repository;

import com.klu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find by email using derived query method
    Optional<User> findByEmail(String email);

    // Find by active status
    List<User> findByIsActive(Boolean isActive);

    // Custom @Query example - JPQL
    @Query("SELECT u FROM User u WHERE u.name = :name")
    List<User> findByUserName(@Param("name") String name);

    // Custom @Query with SQL - search by email pattern
    @Query(value = "SELECT * FROM users WHERE email LIKE %:email%", nativeQuery = true)
    List<User> searchByEmail(@Param("email") String email);

    // Find all active users
    @Query("SELECT u FROM User u WHERE u.isActive = true")
    List<User> findAllActiveUsers();

    // Custom query with multiple parameters
    @Query("SELECT u FROM User u WHERE u.name LIKE %:name% AND u.isActive = :active")
    List<User> findByNameAndActive(@Param("name") String name, @Param("active") Boolean active);

    // Count active users
    @Query("SELECT COUNT(u) FROM User u WHERE u.isActive = true")
    long countActiveUsers();
}



