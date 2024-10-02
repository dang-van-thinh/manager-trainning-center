package thinh.manager.backend.model.dto.student;

import thinh.manager.backend.entity.Student;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import thinh.manager.backend.entity.enums.ERole;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private String id;
    private String fullName;
    private String gender;
    private LocalDate birthDay;
    private String email;
    private String phone;
    private String level;
    private ERole role;
    private String image;
    private String address;
    private Boolean active;
    public static StudentDto toStudentDto(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .phone(student.getPhone())
                .birthDay(student.getBirthDay())
                .email(student.getEmail())
                .fullName(student.getFullName())
                .gender(student.getGender())
                .level(student.getLevel())
                .role(student.getRole())
                .active(student.getActive())
                .address(student.getAddress())
                .image(student.getImage())
                .build();
    }
}
