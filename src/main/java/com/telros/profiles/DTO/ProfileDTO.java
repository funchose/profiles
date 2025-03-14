package com.telros.profiles.DTO;

import com.telros.profiles.model.Profile;
import java.sql.Date;

public class ProfileDTO {

  private final Long id;
  private final String lastName;
  private final String firstName;
  private final String middleName;
  private final Date birthdate;
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


  public ProfileDTO(Profile profile) {
    this.id = profile.getId();
    this.firstName = profile.getFirstName();
    this.lastName = profile.getLastName();
    this.middleName = profile.getMiddleName();
    this.birthdate = profile.getBirthdate();
    this.email = profile.getEmail();
    this.phoneNumber = profile.getPhoneNumber();
  }
}