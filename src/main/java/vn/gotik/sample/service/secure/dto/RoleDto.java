package vn.gotik.sample.service.secure.dto;

import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.gotik.sample.service.secure.entities.Permission;
import vn.gotik.sample.service.secure.entities.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private UUID id;
    private String name;
    private String description;
    private Set<Permission> permissions;

    public static RoleDto from(Role entity) {
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPermissions(entity.getPermissions());
        return dto;
    }
}
