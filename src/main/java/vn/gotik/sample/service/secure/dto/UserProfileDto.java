package vn.gotik.sample.service.secure.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.gotik.sample.service.secure.entities.UserProfile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {

    private UUID id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String dateOfBirth;
    private String idNumber;

    public static UserProfileDto from(UserProfile entity) {
        UserProfileDto dto = new UserProfileDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setLastName(entity.getLastName());
        dto.setDateOfBirth(entity.getDateOfBirth().toString());
        dto.setIdNumber(entity.getIdNumber());
        return dto;
    }

}
