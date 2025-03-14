package com.telros.profiles.service;

import com.telros.profiles.DTO.ProfileContactsDTO;
import com.telros.profiles.DTO.ProfileDTO;
import com.telros.profiles.exceptions.ProfileNotFoundException;
import com.telros.profiles.model.Profile;
import com.telros.profiles.repository.ProfileRepository;
import com.telros.profiles.request.AddProfileRequest;
import com.telros.profiles.request.EditProfileContactsRequest;
import com.telros.profiles.request.EditProfileRequest;
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

  /**
   * @return List of ProfileDTOs created from all profiles entities taken from repository
   */
  public List<ProfileDTO> getProfiles() {
    return profileRepository.findAll().stream()
        .map(ProfileDTO::new)
        .collect(Collectors.toList());
  }

  /**
   * @param id id of profile to search in repository
   * @return ProfileDTO of profile entity
   */
  public ProfileDTO getProfile(Long id) {
    return profileRepository.findById(id).map(ProfileDTO::new)
        .orElseThrow(() -> new ProfileNotFoundException(id));
  }

  /**
   * @return List of ProfileContactsDTOs, created from all profile entities taken from repository
   */
  public List<ProfileContactsDTO> getProfilesContacts() {
    return profileRepository.findAll().stream()
        .map((ProfileContactsDTO::new))
        .collect(Collectors.toList());
  }

  /**
   * @param id id of profile to search in repository
   * @return ProfileContactDTO of profile entity
   */
  public ProfileContactsDTO getProfileContacts(Long id) {
    return profileRepository.findById(id).map(ProfileContactsDTO::new)
        .orElseThrow(() -> new ProfileNotFoundException(id));
  }

  /**
   * If the error occurs during profile creation, rollback to the previous state happens
   *
   * @param request request for profile adding to DB
   * @return id of successfully added new profile
   */
  @Transactional
  public Long addProfile(AddProfileRequest request) {
    var profile = new Profile();
    profile.setFirstName(request.getFirstName());
    profile.setLastName(request.getLastName());
    profile.setMiddleName(request.getMiddleName());
    profile.setEmail(request.getEmail());
    profile.setBirthdate(request.getBirthdate());
    profile.setPhoneNumber(request.getPhoneNumber());
    return profileRepository.save(profile).getId();
  }

  /**
   * Method for editing one or several parameters of the profile in the DB. If the error
   * occurs during editing, rollback to the previous state happens
   *
   * @param id      id of profile to be edited
   * @param request request for profile data editing in the DB
   * @return id of successfully edited profile
   */
  @Transactional
  public Long editProfile(Long id, EditProfileRequest request) {
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

  /**
   * Method for editing one or several parameters of the profile contacts in the DB. If the error
   * occurs during editing, rollback to the previous state happens
   *
   * @param id      id of profile which contacts must be edited
   * @param request request for profile contacts editing in the DB
   * @return id of successfully edited profile
   */
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

  /**
   * @param id id of profile to be deleted from the DB
   * @return id of deleted profile
   */
  public Long deleteProfile(Long id) {
    if (profileRepository.findById(id).isPresent()) {
      profileRepository.deleteById(id);
      return id;
    } else {
      throw new ProfileNotFoundException(id);
    }
  }
}
