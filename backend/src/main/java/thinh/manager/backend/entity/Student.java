package thinh.manager.backend.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Student extends Person{
    private String level; // trinh do hoc van
    @Builder
    public Student(String id , LocalDate birthDay,String gender,String email,String fullName, String phone , String level) {
        super.setId(id);
        super.setBirthDay(birthDay);
        super.setGender(gender);
        super.setPhone(phone);
        super.setFullName(fullName);
        super.setEmail(email);
        this.level = level;
    }
}
