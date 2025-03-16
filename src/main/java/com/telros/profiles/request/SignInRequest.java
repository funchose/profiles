package com.telros.profiles.request;

import jakarta.validation.constraints.NotBlank;

public class SignInRequest {
  @NotBlank(message = "Имя пользователя не может быть пустым")
  private String username;
  @NotBlank(message = "Пароль не может быть пустым")
  private String password;

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
