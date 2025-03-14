package com.telros.profiles.exceptions;

public class ProfileNotFoundException extends RuntimeException {

  public ProfileNotFoundException(Long id) {
    super(String.format("Профиль с id %d не найден", id));
  }
}
