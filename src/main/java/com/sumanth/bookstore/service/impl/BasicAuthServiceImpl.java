package com.sumanth.bookstore.service.impl;

import com.sumanth.bookstore.entity.User;
import com.sumanth.bookstore.repository.AuthRepository;
import com.sumanth.bookstore.service.BasicAuthService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BasicAuthServiceImpl implements BasicAuthService {

  @Autowired
  private AuthRepository authRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void registerUser(User user) {
    if (user == null) {
      throw new IllegalArgumentException("User cannot be null");
    }
    if (user.getPassword() == null || user.getPassword().isEmpty()) {
      throw new IllegalArgumentException("Password cannot be null or empty");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    authRepository.save(user);
  }

  @Override
  public void loginUser(String username, String password) {
//    authRepository.findBy(username);
  }

  @Override
  public void deleteUser(String username) {
    if (username == null || username.isEmpty()) {
      throw new IllegalArgumentException("Username cannot be null or empty");
    }
    User user = authRepository.findByUsername(username);
    if (user != null) {
      authRepository.delete(user);
    }
  }
}
