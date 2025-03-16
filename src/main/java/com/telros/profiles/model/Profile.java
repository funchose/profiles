package com.telros.profiles.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "profile")
public class Profile implements UserDetails {

  @Column(name = "id")
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_generator")
  @SequenceGenerator(name = "profile_generator", sequenceName = "profile_seq", allocationSize = 1)
  private Long id;
  @Column(name = "username", unique = true)
  @NotBlank
  private String username;
  @Column(name = "password")
  @NotBlank
  private String password;
  @Column(name = "last_name", length = 50)
  @NotBlank
  private String lastName;
  @Column(name = "first_name", length = 50)
  @NotBlank
  private String firstName;
  @Column(name = "middle_name", length = 50)
  private String middleName;
  @Column(name = "birthdate")
  @NotNull
  private Date birthdate;
  @Column(name = "email", unique = true, length = 50)
  @NotNull
  @Email
  private String email;
  @Column(name = "phone_number", unique = true, length = 15)
  private String phoneNumber;

  @Override
  public boolean isAccountNonExpired() {
    return UserDetails.super.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return UserDetails.super.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return UserDetails.super.isEnabled();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("user"));
  }

  public Profile setId(Long id) {
    this.id = id;
    return this;
  }

  public Profile setUsername(String username) {
    this.username = username;
    return this;
  }

  public Profile setPassword(String password) {
    this.password = password;
    return this;
  }

  public Profile setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public Profile setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public Profile setMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public Profile setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
    return this;
  }

  public Profile setEmail(String email) {
    this.email = email;
    return this;
  }

  public Profile setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

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
}
