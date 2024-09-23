package thinh.manager.backend.model.dto.user;

import thinh.manager.backend.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import thinh.manager.backend.entity.enums.ERole;

import java.time.LocalDate;

@Data
@Builder
public class UserDto {
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

    private ERole role;

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .birthDay(user.getBirthDay())
                .email(user.getEmail())
                .phone(user.getPhone())
                .fullName(user.getFullName())
                .gender(user.getGender())
                .username(user.getUserName())
                .password(user.getPassword())
                .phone(user.getPhone())
                .position(user.getPosition())
                .role(user.getRole())
                .build();
    }
}
