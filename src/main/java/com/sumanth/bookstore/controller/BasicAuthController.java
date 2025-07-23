package com.sumanth.bookstore.controller;

import com.sumanth.bookstore.entity.User;
import com.sumanth.bookstore.service.BasicAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public ResponseEntity<String> register(@RequestBody User user) {
    basicAuthService.registerUser(user);
    return new ResponseEntity<>("User signed up!", HttpStatus.CREATED);
  }

  @DeleteMapping("/deleteUser/{username}")
  public ResponseEntity<String> deleteUser(@PathVariable String username) {
    basicAuthService.deleteUser(username);
    return new ResponseEntity<>("User deleted!", HttpStatus.OK);
  }
}
