package com.telros.profiles.service;

import com.telros.profiles.model.Profile;
import com.telros.profiles.request.SignInRequest;
import com.telros.profiles.request.SignUpRequest;
import com.telros.profiles.security.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final ProfileService profileService;

  public AuthService(JwtService jwtService,
                     PasswordEncoder passwordEncoder,
                     AuthenticationManager authenticationManager,
                     ProfileService profileService) {
    this.jwtService = jwtService;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.profileService = profileService;
  }

  @Transactional
  public void signUp(SignUpRequest request) {
    var profile = new Profile();
    profile.setFirstName(request.getFirstName());
    profile.setLastName(request.getLastName());
    profile.setMiddleName(request.getMiddleName());
    profile.setEmail(request.getEmail());
    profile.setBirthdate(request.getBirthdate());
    profile.setPhoneNumber(request.getPhoneNumber());
    profile.setUsername(request.getUsername());
    profile.setPassword(passwordEncoder.encode(request.getPassword()));
    profileService.addProfile(profile);
  }

  @Transactional
  public String signIn(SignInRequest request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        request.getUsername(),
        request.getPassword()
    ));
    var profile = profileService.loadUserByUsername(request.getUsername());
    return jwtService.generateToken(profile);
  }

}
