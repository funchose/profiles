package com.telros.profiles.service;

import com.telros.profiles.ProfilesApplication;
import com.telros.profiles.controller.AuthController;
import com.telros.profiles.exceptions.ProfileNotFoundException;
import com.telros.profiles.model.Profile;
import com.telros.profiles.repository.ProfileRepository;
import com.telros.profiles.request.EditProfileRequest;
import com.telros.profiles.security.jwt.JwtService;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProfilesApplication.class)
public class ProfileServiceTest {
  @Autowired
  private ProfileRepository profileRepository;
  @Autowired
  private JwtService jwtService;
  @Autowired
  AuthService authService;
  @Autowired
  AuthController authController;
  @Autowired
  ProfileService profileService;
  private final java.util.Date date = new GregorianCalendar(
      2014, Calendar.MARCH, 20).getTime();
  private final Date requestDate = new Date(date.getTime());
  private final Profile profile = new Profile()
      .setId(null)
      .setUsername("admin")
      .setPassword("admin")
      .setLastName("Ivanov")
      .setFirstName("Ivan")
      .setBirthdate(requestDate)
      .setMiddleName("Ivanovich")
      .setEmail("ivanov@mail.ru")
      .setPhoneNumber("89877655443");


  @Test
  @Transactional
  public void addProfileTest() {
    profileService.addProfile(profile);
    Assertions.assertEquals("Ivan",
        profileRepository.findByEmail("ivanov@mail.ru").getFirstName());
    profileRepository.deleteById(profileRepository.findByEmail("ivanov@mail.ru").getId());
  }

  @Test
  @Transactional
  public void editProfileTest() {
    Long id = profileService.addProfile(profile);
    var editRequest = new EditProfileRequest();
    editRequest.setFirstName("Anton");
    editRequest.setLastName("Antonov");
    profileService.editProfile(id, editRequest);
    Assertions.assertEquals(editRequest.getFirstName(),
        profileRepository.findById(id).get().getFirstName());
    Assertions.assertEquals(editRequest.getLastName(),
        profileRepository.findById(id).get().getLastName());
    profileRepository.deleteById(profileRepository.findByEmail("ivanov@mail.ru").getId());
  }

  @Test
  @Transactional
  public void editProfileContactsTest() {
    Long id = profileService.addProfile(profile);
    var editRequest = new EditProfileRequest();
    editRequest.setEmail("ivanivanov@mail.ru");
    editRequest.setPhoneNumber("89674792857");
    profileService.editProfile(id, editRequest);
    Assertions.assertEquals(editRequest.getEmail(),
        profileRepository.findById(id).get().getEmail());
    Assertions.assertEquals(editRequest.getPhoneNumber(),
        profileRepository.findById(id).get().getPhoneNumber());
  }

  @Test
  @Transactional
  public void deleteProfileTest() {
    Long id = profileService.addProfile(profile);
    profileService.deleteProfile(id);
    Assert.assertThrows(ProfileNotFoundException.class, () -> profileService.getProfile(id));

  }
}
