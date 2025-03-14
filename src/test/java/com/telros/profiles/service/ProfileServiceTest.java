package com.telros.profiles.service;

import com.telros.profiles.ProfilesApplication;
import com.telros.profiles.exceptions.ProfileNotFoundException;
import com.telros.profiles.repository.ProfileRepository;
import com.telros.profiles.request.AddProfileRequest;
import com.telros.profiles.request.EditProfileRequest;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProfilesApplication.class)
public class ProfileServiceTest{
  @Autowired
  private ProfileService service;
  @Autowired
  private ProfileRepository repository;

  @Test
  public void addProfileTest() {
    var request = new AddProfileRequest();
    request.setFirstName("Ivan");
    request.setLastName("Ivanov");
    request.setMiddleName("Ivanovich");
    request.setPhoneNumber("89877655443");
    java.util.Date date = new GregorianCalendar(
        2014, Calendar.MARCH, 20).getTime();
    Date requestDate = new Date(date.getTime());
    request.setBirthdate(requestDate);
    request.setEmail("ivanov@mail.ru");
    service.addProfile(request);
    Assertions.assertEquals("Ivan",
        repository.findByEmail("ivanov@mail.ru").getFirstName());
    repository.deleteById(repository.findByEmail("ivanov@mail.ru").getId());
  }

  @Test
  public void addProfileWithTheSameEmailTest() {
    var request = new AddProfileRequest();
    request.setFirstName("Ivan");
    request.setLastName("Ivanov");
    request.setMiddleName("Ivanovich");
    request.setPhoneNumber("89877655443");
    java.util.Date date = new GregorianCalendar(
        2014, Calendar.MARCH, 20).getTime();
    Date requestDate = new Date(date.getTime());
    request.setBirthdate(requestDate);
    request.setEmail("ivanov@mail.ru");
    service.addProfile(request);
    Assertions.assertThrows(DataIntegrityViolationException.class,
        () -> service.addProfile(request));
    repository.deleteById(repository.findByEmail("ivanov@mail.ru").getId());
  }

  @Test
  public void editProfileTest() {
    var addRequest = new AddProfileRequest();
    addRequest.setFirstName("Ivan");
    addRequest.setLastName("Ivanov");
    addRequest.setMiddleName("Ivanovich");
    addRequest.setPhoneNumber("89877655443");
    java.util.Date date = new GregorianCalendar(
        2014, Calendar.MARCH, 20).getTime();
    Date requestDate = new Date(date.getTime());
    addRequest.setBirthdate(requestDate);
    addRequest.setEmail("ivanov@mail.ru");
    Long id = service.addProfile(addRequest);
    var editRequest = new EditProfileRequest();
    editRequest.setFirstName("Anton");
    editRequest.setLastName("Antonov");
    service.editProfile(id, editRequest);
    Assertions.assertEquals(editRequest.getFirstName(),
        repository.findById(id).get().getFirstName());
    Assertions.assertEquals(editRequest.getLastName(),
        repository.findById(id).get().getLastName());
  }

  @Test
  public void editProfileContactsTest() {
    var addRequest = new AddProfileRequest();
    addRequest.setFirstName("Ivan");
    addRequest.setLastName("Ivanov");
    addRequest.setMiddleName("Ivanovich");
    addRequest.setPhoneNumber("89877655443");
    java.util.Date date = new GregorianCalendar(
        2014, Calendar.MARCH, 20).getTime();
    Date requestDate = new Date(date.getTime());
    addRequest.setBirthdate(requestDate);
    addRequest.setEmail("ivanov@mail.ru");
    Long id = service.addProfile(addRequest);
    var editRequest = new EditProfileRequest();
    editRequest.setEmail("ivanivanov@mail.ru");
    editRequest.setPhoneNumber("89674792857");
    service.editProfile(id, editRequest);
    Assertions.assertEquals(editRequest.getEmail(),
        repository.findById(id).get().getEmail());
    Assertions.assertEquals(editRequest.getPhoneNumber(),
        repository.findById(id).get().getPhoneNumber());
  }

  @Test
  public void deleteProfileTest() {
    var addRequest = new AddProfileRequest();
    addRequest.setFirstName("Ivan");
    addRequest.setLastName("Ivanov");
    addRequest.setMiddleName("Ivanovich");
    addRequest.setPhoneNumber("89877655443");
    java.util.Date date = new GregorianCalendar(
        2014, Calendar.MARCH, 20).getTime();
    Date requestDate = new Date(date.getTime());
    addRequest.setBirthdate(requestDate);
    addRequest.setEmail("ivanov@mail.ru");
    Long id = service.addProfile(addRequest);
    service.deleteProfile(id);
    Assert.assertThrows(ProfileNotFoundException.class, () -> service.getProfile(id));
  }
}
