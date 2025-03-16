package com.telros.profiles.repository;

import com.telros.profiles.model.Profile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
  Profile findByEmail(String email);

  Optional<Profile> findByUsername(String username);
}
