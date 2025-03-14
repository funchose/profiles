package com.telros.profiles.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "profile ")
public class Profile {
  @Column(name = "id")
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_generator")
  @SequenceGenerator(name = "profile_generator", sequenceName = "profile_seq", allocationSize = 1)
  private Long id;
  @Column(name = "last_name")
  @NotBlank
  private String lastName;
  @Column(name = "first_name")
  @NotBlank
  private String firstName;
  @Column(name = "middle_name")
  private String middleName;
  @Column(name = "birthdate")
  @NotNull
  private Date birthdate;
  @Column(name = "email", unique = true)
  @NotNull
  @Email

  private String email;
  @Column(name = "phone_number", unique = true)
  private String phoneNumber;

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
