package vn.gotik.sample.service.secure.services;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import vn.gotik.sample.service.secure.entities.Permission;
import vn.gotik.sample.service.secure.entities.User;

public interface UserService {

    public Optional<User> findById(UUID id);

    public Optional<User> findByEmail(String email);

    public User save(User user);

    public Set<Permission> getPermissions(UUID userId);
}
