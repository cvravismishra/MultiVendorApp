package com.security.auth_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.security.auth_app.configuration.JwtService;
import com.security.auth_app.dao.AuthenticationRequest;
import com.security.auth_app.dao.RegisterRequest;
import com.security.auth_app.entity.User;
import com.security.auth_app.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private AuthenticationManager authenticationManager;

  public String registerUser(RegisterRequest registerRequest) {

    User user = User.builder()
    .userName(registerRequest.getUserName())
    .email(registerRequest.getEmail())
    .password(registerRequest.getPassword())
    .mobile(registerRequest.getMobile())
    .role(registerRequest.getRole())
    .build();

    userRepository.save(user);

    String jwtToken = jwtService.generateToken(user);

    return jwtToken;
  }
  
  public String authenticateUser(AuthenticationRequest authRequest){

    authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
  
		User user = userRepository.findByEmail(authRequest.getEmail()).orElse(new User());
		System.out.println(user);
		String jwtToken = jwtService.generateToken(user);

		return jwtToken;
	}
}
