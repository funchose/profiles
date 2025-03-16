package com.telros.profiles.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.sql.Date;

public class SignUpRequest {
  @Size(min = 5, max = 25,
      message = "Имя пользователя должно содержать от 5 до 25 символов")
  @NotBlank(message = "Имя пользователя не может быть пустым")
  private String username;

  @Size(min = 5, max = 25,
      message = "Длина пароля не должна быть меньше 5 и больше 25 символов")
  @NotBlank(message = "Пароль не может быть пустым")
  private String password;
  @NotEmpty(message = "Поле lastName не должно быть пустым")
  private String lastName;
  @NotEmpty(message = "Поле firstName не должно быть пустым")
  private String firstName;
  private String middleName;
  private Date birthdate;
  @NotEmpty(message = "Поле email не должно быть пустым")
  @Email(message = "Email должен быть в формате: name@domain")
  private String email;

  private String phoneNumber;

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
