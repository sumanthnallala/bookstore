package com.sumanth.bookstore.repository;

import com.sumanth.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User, Long> {
  void findByUsername(String username);
}
