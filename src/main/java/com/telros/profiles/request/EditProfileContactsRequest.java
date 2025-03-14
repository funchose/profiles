package com.telros.profiles.request;

import jakarta.validation.constraints.Email;

public class EditProfileContactsRequest {
  private String lastName;
  private String firstName;
  @Email(message = "Email должен быть в формате: name@domain")
  private String email;
  private String phoneNumber;

  public String getLastName() {
    return lastName;
  }

  public String getFirstName() {
    return firstName;
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

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
