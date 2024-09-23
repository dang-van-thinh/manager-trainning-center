package thinh.manager.backend.model.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateUserDto {
    @NotNull(message = "Không được để trống trường này !")
    private String username;
    @NotNull(message = "Không được để trống trường này !")

    private String password;
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
    private String position;
}
