package vn.gotik.sample.service.secure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.gotik.sample.service.secure.entities.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
