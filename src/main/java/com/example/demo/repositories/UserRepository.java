package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByEmailOrUsername(String emailOrUsername);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String username);
}
