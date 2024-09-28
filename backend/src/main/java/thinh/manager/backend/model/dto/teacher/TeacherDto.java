package thinh.manager.backend.model.dto.teacher;

import thinh.manager.backend.entity.Teacher;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import thinh.manager.backend.entity.enums.ERole;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDto {
    private String id;
    @NotNull(message = "Không được để trống trường này !")
    private String fullName;
    @NotNull(message = "Không được để trống trường này !")
    private String gender;
    @NotNull(message = "Không được để trống trường này !")
    private LocalDate birthDay;
    @NotNull(message = "Không được để trống trường này !")
    private String email;
    @NotNull(message = "Không được để trống trường này !")
    private String phone;
    @NotNull(message = "Không được để trống trường này !")
    private String specialize; // chuyên môn
    @NotNull(message = "Không được để trống trường này !")
    private String qualifications; // trình độ
    @NotNull(message = "Không được để trống trường này !")
    private Double hourlyRate;
    private ERole role;

    public static TeacherDto toTeacherDto(Teacher teacher) {
        return TeacherDto.builder()
                .id(teacher.getId())
                .birthDay(teacher.getBirthDay())
                .email(teacher.getEmail())
                .phone(teacher.getPhone())
                .specialize(teacher.getSpecialize())
                .fullName(teacher.getFullName())
                .gender(teacher.getGender())
                .qualifications(teacher.getQualifications())
                .hourlyRate(teacher.getHourlyRate())
                .role(teacher.getRole())
                .build();
    }

}
