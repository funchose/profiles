package com.telros.profiles.exceptions;

public class ProfileNotFoundException extends RuntimeException{

  public ProfileNotFoundException(Long id) {
    super(String.format("Profile with id %d is not found", id));
  }
}
