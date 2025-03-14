package com.telros.profiles.service;

import com.telros.profiles.DTO.ProfileContactsDTO;
import com.telros.profiles.DTO.ProfileDTO;
import com.telros.profiles.exceptions.ProfileNotFoundException;
import com.telros.profiles.model.Profile;
import com.telros.profiles.repository.ProfileRepository;
import com.telros.profiles.request.AddOrEditProfileRequest;
import com.telros.profiles.request.EditProfileContactsRequest;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {
  private final ProfileRepository profileRepository;

  public ProfileService(ProfileRepository profileRepository) {
    this.profileRepository = profileRepository;
  }

  public List<ProfileDTO> getProfiles() {
    return profileRepository.findAll().stream()
        .map(ProfileDTO::new)
        .collect(Collectors.toList());
  }

  public ProfileDTO getProfile(Long id) {
    return profileRepository.findById(id).map(ProfileDTO::new)
        .orElseThrow(() -> new ProfileNotFoundException(id));
  }

  public List<ProfileContactsDTO> getProfilesContacts() {
    return profileRepository.findAll().stream()
        .map((ProfileContactsDTO::new))
        .collect(Collectors.toList());
  }

  public ProfileContactsDTO getProfileContacts(Long id) {
    return profileRepository.findById(id).map(ProfileContactsDTO::new)
        .orElseThrow(() -> new ProfileNotFoundException(id));
  }

  @Transactional
  public Long addProfile(AddOrEditProfileRequest request) {
    var profile = new Profile();
    profile.setFirstName(request.getFirstName());
    profile.setLastName(request.getLastName());
    profile.setMiddleName(request.getMiddleName());
    profile.setEmail(request.getEmail());
    profile.setBirthdate(request.getBirthdate());
    profile.setPhoneNumber(request.getPhoneNumber());
    return profileRepository.save(profile).getId();
  }

  @Transactional
  public Long editProfile(Long id, AddOrEditProfileRequest request) {
    var profile = profileRepository.findById(id)
        .orElseThrow(() -> new ProfileNotFoundException(id));
    if (request.getFirstName() != null) {
      profile.setFirstName(request.getFirstName());
    }
    if (request.getMiddleName() != null) {
      profile.setMiddleName(request.getMiddleName());
    }
    if (request.getLastName() != null) {
      profile.setLastName(request.getLastName());
    }
    if (request.getBirthdate() != null) {
      profile.setBirthdate(request.getBirthdate());
    }
    if (request.getEmail() != null) {
      profile.setEmail(request.getEmail());
    }
    if (request.getPhoneNumber() != null) {
      profile.setPhoneNumber(request.getPhoneNumber());
    }
    return profileRepository.save(profile).getId();
  }

  @Transactional
  public Long editProfileContacts(Long id, EditProfileContactsRequest request) {
    var profile = profileRepository.findById(id)
        .orElseThrow(() -> new ProfileNotFoundException(id));
    if (request.getFirstName() != null) {
      profile.setFirstName(request.getFirstName());
    }
    if (request.getLastName() != null) {
      profile.setLastName(request.getLastName());
    }
    if (request.getEmail() != null) {
      profile.setEmail(request.getEmail());
    }
    if (request.getPhoneNumber() != null) {
      profile.setPhoneNumber(request.getPhoneNumber());
    }
    return profileRepository.save(profile).getId();
  }

  public Long deleteProfile(Long id) {
    if (profileRepository.findById(id).isPresent()) {
      profileRepository.deleteById(id);
      return id;
    } else {
      throw new ProfileNotFoundException(id);
    }
  }
}
