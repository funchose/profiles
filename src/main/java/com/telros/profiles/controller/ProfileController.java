package com.telros.profiles.controller;

import com.telros.profiles.DTO.ProfileContactsDTO;
import com.telros.profiles.DTO.ProfileDTO;
import com.telros.profiles.request.AddProfileRequest;
import com.telros.profiles.request.EditProfileContactsRequest;
import com.telros.profiles.request.EditProfileRequest;
import com.telros.profiles.service.ProfileService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {
  private final ProfileService profileService;

  public ProfileController(ProfileService profileService) {
    this.profileService = profileService;
  }

  @GetMapping(value = "/profiles")
  public List<ProfileDTO> getProfiles() {
    return profileService.getProfiles();
  }

  @GetMapping(value = "/profiles/{id}")
  public ProfileDTO getProfile(@PathVariable Long id) {
    return profileService.getProfile(id);
  }

  @GetMapping(value = "/profiles/contacts")
  public List<ProfileContactsDTO> getProfilesContacts() {
    return profileService.getProfilesContacts();
  }

  @GetMapping(value = "/profiles/contacts/{id}")
  public ProfileContactsDTO getProfileContacts(@PathVariable Long id) {
    return profileService.getProfileContacts(id);
  }

  @PostMapping(value = "/profiles")
  public Long addProfile(@RequestBody @Valid AddProfileRequest request) {
    return profileService.addProfile(request);
  }

  @PutMapping(value = "/profiles/{id}")
  public Long editProfile(@PathVariable Long id,
                          @RequestBody @Valid EditProfileRequest request) {
    return profileService.editProfile(id, request);
  }

  @PutMapping(value = "profiles/contacts/{id}")
  public Long editProfileContacts(@PathVariable Long id,
                                  @RequestBody @Valid EditProfileContactsRequest request) {
    return profileService.editProfileContacts(id, request);
  }

  @DeleteMapping(value = "profiles/{id}")
  public Long deleteProfile(@PathVariable Long id) {
    return profileService.deleteProfile(id);
  }
}
