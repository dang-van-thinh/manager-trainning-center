package thinh.manager.backend.model.dto.manager;

import thinh.manager.backend.entity.Mananger;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ManagerDto {
    private String id;
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

    public static ManagerDto toMangerDto(Mananger mananger) {
        return ManagerDto.builder()
                .id(mananger.getId())
                .birthDay(mananger.getBirthDay())
                .email(mananger.getEmail())
                .phone(mananger.getPhone())
                .fullName(mananger.getFullName())
                .gender(mananger.getGender())
                .username(mananger.getUserName())
                .password(mananger.getPassword())
                .phone(mananger.getPhone())
                .position(mananger.getPosition())
                .build();
    }
}
