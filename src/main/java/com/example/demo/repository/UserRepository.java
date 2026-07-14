package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // used to check "does this username already exist" before creating/updating
    Optional<User> findByUsername(String username);

    // used to check "does this email already exist" before creating/updating
    Optional<User> findByEmail(String email);

    // case-insensitive partial match, same pattern as
    // Book's findByTitleContainingIgnoreCase — powers our /search endpoint
    List<User> findByUsernameContainingIgnoreCase(String keyword);

    // powers a simple "list only active users" filter if you ever need it
    List<User> findByActiveTrue();
}