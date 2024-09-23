package thinh.manager.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import thinh.manager.backend.entity.enums.ERole;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User extends Person{
    private String position;
    private String userName;
    private String password;
    @Builder
    public User(String id , LocalDate birthDay, String gender, String email, String position,
                String fullName , String phone , String userName, String password , ERole role) {
        super.setId(id);
        super.setGender(gender);
        super.setPhone(phone);
        super.setEmail(email);
        super.setBirthDay(birthDay);
        super.setFullName(fullName);
        super.setRole(role);

        this.position = position;
        this.userName = userName;
        this.password = password;
    }
}
