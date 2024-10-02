package thinh.manager.backend.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import thinh.manager.backend.entity.enums.ERole;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Student extends Person{
    private String level; // trinh do hoc van
    private Boolean active; // trang thai tai khoan

    @Builder
    public Student(String id , LocalDate birthDay, String gender, String email, String fullName, String phone , String level, ERole role,Boolean active, String address,String image) {
        super.setId(id);
        super.setBirthDay(birthDay);
        super.setGender(gender);
        super.setPhone(phone);
        super.setFullName(fullName);
        super.setEmail(email);
        super.setRole(role);
        super.setAddress(address);
        super.setImage(image);

        this.level = level;
        this.active = active;
    }
}
