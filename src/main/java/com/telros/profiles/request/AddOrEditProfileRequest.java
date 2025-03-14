package com.telros.profiles.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.sql.Date;

public class AddOrEditProfileRequest {
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
}
