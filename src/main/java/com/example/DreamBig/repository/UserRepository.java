package com.example.DreamBig.repository;

import com.example.DreamBig.entity.UserEntity;
import com.example.DreamBig.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<User> findByEmail(String email);
}

