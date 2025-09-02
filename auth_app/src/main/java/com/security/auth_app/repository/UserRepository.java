package com.security.auth_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.auth_app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

  Optional<User> findByEmail(String username);
  
}
