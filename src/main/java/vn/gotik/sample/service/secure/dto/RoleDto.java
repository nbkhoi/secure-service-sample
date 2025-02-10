package vn.gotik.sample.service.secure.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.gotik.sample.service.secure.entities.Role;
import vn.gotik.sample.service.secure.types.Authority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private String role;
    private Set<Authority> authorities;

    public static RoleDto from(Role entity) {
        RoleDto dto = new RoleDto();
        dto.setRole(entity.getRole());
        dto.setAuthorities(entity.getAuthorities());
        return dto;
    }
}
