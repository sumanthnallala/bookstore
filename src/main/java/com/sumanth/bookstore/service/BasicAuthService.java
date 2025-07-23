package com.sumanth.bookstore.service;

import com.sumanth.bookstore.entity.User;

public interface BasicAuthService {

  void registerUser(User user);

  void loginUser(String username, String password);
}
