package vn.gotik.sample.service.secure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.gotik.sample.service.secure.entities.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
}
