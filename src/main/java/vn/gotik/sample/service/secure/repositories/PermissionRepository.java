package vn.gotik.sample.service.secure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.gotik.sample.service.secure.entities.Permission;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
}