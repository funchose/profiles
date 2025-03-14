package com.telros.profiles.request;

import jakarta.validation.constraints.Email;
import java.sql.Date;

public class EditProfileRequest {
  private String lastName;
  private String firstName;
  private String middleName;
  private Date birthdate;

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
