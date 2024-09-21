package thinh.manager.backend.entity;

import jakarta.persistence.*;
import lombok.*;

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
}
