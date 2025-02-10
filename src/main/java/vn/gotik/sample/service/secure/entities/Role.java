package vn.gotik.sample.service.secure.entities;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import vn.gotik.sample.service.secure.types.Authority;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "role", nullable = false)
    private String role;

    @ElementCollection
    @Column(name = "authorities")
    private Set<Authority> authorities;
}
