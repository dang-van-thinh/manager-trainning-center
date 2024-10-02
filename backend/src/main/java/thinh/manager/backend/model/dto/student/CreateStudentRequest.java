package thinh.manager.backend.model.dto.student;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateStudentRequest {
    @NotEmpty(message = "Không được để trống trường này !")
    private String fullName;
    @NotEmpty(message = "Không được để trống trường này !")
    private String gender;
    @NotNull(message = "Không được để trống trường này !")
    private LocalDate birthDay;
    @NotEmpty(message = "Không được để trống trường này !")
    private String email;
    @NotEmpty(message = "Không được để trống trường này !")
    private String phone;
    @NotEmpty(message = "Không được để trống trường này !")
    private String level;
    @NotEmpty(message = "Không được để trống trường này !")
    private String address;
}
