package vn.gotik.sample.service.secure.entities;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @OneToOne
    @MapsId
    private User user;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;
    @Column(name = "id_number", nullable = false, unique = true)
    private String idNumber;
}
