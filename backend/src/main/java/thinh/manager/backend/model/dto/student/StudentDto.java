package thinh.manager.backend.model.dto.student;

import thinh.manager.backend.entity.Student;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private String id;
    @NotEmpty(message = "Không được để trống !")
    private String fullName;
    @NotEmpty(message = "Không được để trống !")
    private String gender;
    @NotNull(message = "Không được để trống !")
    private LocalDate birthDay;
    @NotEmpty(message = "Không được để trống !")
    private String email;
    @NotEmpty(message = "Không được để trống !")
    private String phone;
    @NotEmpty(message = "Không được để trống !")
    private String level;

    public static StudentDto toStudentDto(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .phone(student.getPhone())
                .birthDay(student.getBirthDay())
                .email(student.getEmail())
                .fullName(student.getFullName())
                .gender(student.getGender())
                .level(student.getLevel())
                .build();
    }
}
