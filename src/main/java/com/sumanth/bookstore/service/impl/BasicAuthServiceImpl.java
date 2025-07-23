package com.sumanth.bookstore.service.impl;

import com.sumanth.bookstore.entity.User;
import com.sumanth.bookstore.repository.AuthRepository;
import com.sumanth.bookstore.service.BasicAuthService;
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
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    authRepository.save(user);
  }

  @Override
  public void loginUser(String username, String password) {
//    authRepository.findBy(username);
  }

  @Override
  public void deleteUser(String username) {
    User user = authRepository.findByUsername(username);
    if (user != null) {
      authRepository.delete(user);
    }
  }
}
