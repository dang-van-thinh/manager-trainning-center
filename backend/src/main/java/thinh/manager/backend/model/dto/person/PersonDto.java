package thinh.manager.backend.model.dto.person;

import thinh.manager.backend.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDto {
    private String id;
    private String username;
    private String password;
    private String fullName;
    private String EGender;
    private LocalDate birthDay;
    private String email;
    private String phone;
    private Integer roleID;

    public static PersonDto toPersonDto(Person person) {
        return PersonDto.builder()
                .id(person.getId())
                .birthDay(person.getBirthDay())
                .email(person.getEmail())
                .fullName(person.getFullName())
                .EGender(person.getGender())
                .build();
    }
}
