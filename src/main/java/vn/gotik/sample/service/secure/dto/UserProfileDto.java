package vn.gotik.sample.service.secure.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.gotik.sample.service.secure.entities.UserProfile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {

    private String username;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String citizenId;
    private Set<RoleDto> roles = new HashSet<>();

    public static UserProfileDto from(UserProfile entity) {
        UserProfileDto dto = new UserProfileDto();
        dto.setUsername(entity.getUsername().toString());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setFirstName(entity.getFirstName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setLastName(entity.getLastName());
        dto.setCitizenId(entity.getCitizenId());
        entity.getRoles().stream().map(RoleDto::from).forEach(dto.getRoles()::add);
        return dto;
    }
}
