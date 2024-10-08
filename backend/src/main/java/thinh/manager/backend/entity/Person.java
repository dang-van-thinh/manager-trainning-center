package thinh.manager.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import thinh.manager.backend.entity.enums.ERole;

import java.time.LocalDate;
@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fullName;
    private String gender;
    private LocalDate birthDay;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private ERole role;
    private String address;
    private String image;
}
