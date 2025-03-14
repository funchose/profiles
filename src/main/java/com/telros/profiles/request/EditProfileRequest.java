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
}
