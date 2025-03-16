package com.telros.profiles.controller;

import com.telros.profiles.DTO.ProfileContactsDTO;
import com.telros.profiles.DTO.ProfileDTO;
import com.telros.profiles.model.Profile;
import com.telros.profiles.request.EditProfileContactsRequest;
import com.telros.profiles.request.EditProfileRequest;
import com.telros.profiles.service.ProfileService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

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

  @PutMapping(value = "/profiles/{id}")
  public Long editProfile(@PathVariable Long id,
                          @RequestBody @Valid EditProfileRequest request,
                          @AuthenticationPrincipal Profile profile) {
    if (profile.getId().equals(id)) {
      return profileService.editProfile(id, request);
    } else {
      throw new HttpClientErrorException(HttpStatus.FORBIDDEN,
          "Вы не можете редактировать чужой профиль");
    }
  }

  @PutMapping(value = "profiles/contacts/{id}")
  public Long editProfileContacts(@PathVariable Long id,
                                  @RequestBody @Valid EditProfileContactsRequest request,
                                  @AuthenticationPrincipal Profile profile) {
    if (profile.getId().equals(id)) {
      return profileService.editProfileContacts(id, request);
    } else {
      throw new HttpClientErrorException(HttpStatus.FORBIDDEN,
          "Вы не можете редактировать чужой профиль");
    }
  }

  @DeleteMapping(value = "profiles/{id}")
  public Long deleteProfile(@PathVariable Long id,
                            @AuthenticationPrincipal Profile profile) {
    if (profile.getId().equals(id)) {
    return profileService.deleteProfile(id);
    } else {
      throw new HttpClientErrorException(HttpStatus.FORBIDDEN,
          "Вы не можете удалить чужой профиль");
    }
  }
}
