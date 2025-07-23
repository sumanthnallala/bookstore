package com.sumanth.bookstore.controller;

import com.sumanth.bookstore.entity.User;
import com.sumanth.bookstore.service.BasicAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class BasicAuthController {

  @Autowired
  private BasicAuthService basicAuthService;

  @PostMapping("/signup")
  public String register(@RequestBody User user) {
    basicAuthService.registerUser(user);
    return "User signed up!";
  }
}
