package com.security.auth_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.security.auth_app.dao.AuthenticationRequest;
import com.security.auth_app.dao.RegisterRequest;
import com.security.auth_app.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest)
  {
      return ResponseEntity.ok(userService.registerUser(registerRequest));
  }

  @PostMapping("/authentication")
  public ResponseEntity<String> authentiction(@RequestBody AuthenticationRequest authenticationRequest)
  {
      return ResponseEntity.ok(userService.authenticateUser(authenticationRequest));
  }
  
}
