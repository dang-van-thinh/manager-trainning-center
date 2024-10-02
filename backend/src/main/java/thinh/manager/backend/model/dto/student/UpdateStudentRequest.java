package thinh.manager.backend.model.dto.student;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateStudentRequest {
    @NotEmpty(message = "Không được để trống trường này !")
    private String fullName;
    @NotEmpty(message = "Không được để trống trường này !")
    private String gender;
    @NotEmpty(message = "Không được để trống trường này !")
    private LocalDate birthDay;
    @NotEmpty(message = "Không được để trống trường này !")
    private String email;
    @NotEmpty(message = "Không được để trống trường này !")
    private String phone;
    @NotEmpty(message = "Không được để trống trường này !")
    private String level;
    @NotEmpty(message = "Không được để trống trường này !")
    private String image;
    @NotEmpty(message = "Không được để trống trường này !")
    private String address;
}
