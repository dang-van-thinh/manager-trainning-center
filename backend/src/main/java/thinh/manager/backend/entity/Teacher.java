package thinh.manager.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends Person {
    private String specialize; // chuyên môn
    private String qualifications; // trình độ
    @Column(name = "hourly_rate")
    private Double hourlyRate; // luong theo gio

    @Builder
    public Teacher(String id, LocalDate birthDay, String gender, String specialize, String qualifications, String email,
                   String fullName, String phone, double hourlyRate) {
        super.setId(id);
        super.setBirthDay(birthDay);
        super.setGender(gender);
        super.setEmail(email);
        super.setFullName(fullName);
        super.setPhone(phone);

        this.specialize = specialize;
        this.qualifications = qualifications;
        this.hourlyRate = hourlyRate;
    }


}
