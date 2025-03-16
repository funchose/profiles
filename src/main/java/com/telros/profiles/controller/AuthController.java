package com.telros.profiles.controller;

import com.telros.profiles.request.SignInRequest;
import com.telros.profiles.request.SignUpRequest;
import com.telros.profiles.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
  @Autowired
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/auth/sign-up")
  public void signUp(@RequestBody @Valid SignUpRequest request) {
    authService.signUp(request);
  }

  @PostMapping("/auth/sign-in")
  public String signIn(@RequestBody @Valid SignInRequest request) {
    return authService.signIn(request);
  }
}
