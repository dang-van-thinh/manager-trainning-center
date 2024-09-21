package thinh.manager.backend.model.dto.teacher;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateTeacherDto {

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
}
