package thinh.manager.backend.model.dto.enrollment;

import thinh.manager.backend.entity.Enrollment;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EnrollmentDto {
    private String id;
    @NotNull(message = "Không được để trống trường này !")
    private String classesId;

    @NotNull(message = "Không được để trống trường này !")
    private String studentId;
    private LocalDate timeJoin;

    public static EnrollmentDto enrollmentDto (Enrollment enrollment){
        return EnrollmentDto.builder()
                .id(enrollment.getId())
                .classesId(enrollment.getClassesId())
                .studentId(enrollment.getStudentId())
                .timeJoin(enrollment.getTimeJoin())
                .build();
    }
}
