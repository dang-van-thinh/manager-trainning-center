package thinh.manager.backend.entity;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Mananger extends Person{
    private String position;
    private String userName;
    private String password;
    @Builder
    public Mananger(String id , LocalDate birthDay, String gender, String email, String position,
                     String fullName ,String phone , String userName,String password) {
        super.setId(id);
        super.setGender(gender);
        super.setPhone(phone);
        super.setEmail(email);
        super.setBirthDay(birthDay);
        super.setFullName(fullName);

        this.position = position;
        this.userName = userName;
        this.password = password;
    }
}
