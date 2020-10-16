package com.lcd.jwt.api.repository;

import com.lcd.jwt.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  User findByUsername(String userName);

  boolean existsByEmail(String email);

  boolean existsByUsername(String username);
}
