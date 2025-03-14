package com.telros.profiles.DTO;

import com.telros.profiles.model.Profile;

public class ProfileContactsDTO {
  private final Long id;
  private final String lastName;
  private final String firstName;
  private final String email;
  private final String phoneNumber;


  public Long getId() {
    return id;
  }

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

  public ProfileContactsDTO(Profile profile) {
    this.id = profile.getId();
    this.lastName = profile.getLastName();
    this.firstName = profile.getFirstName();
    this.email = profile.getEmail();
    this.phoneNumber = profile.getPhoneNumber();
  }
}
