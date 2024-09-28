package thinh.manager.backend.model.response.teacher;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import thinh.manager.backend.entity.Classes;
import thinh.manager.backend.entity.enums.ERole;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class TeacherClass {
    private String id;
    private String fullName;
    private String gender;
    private LocalDate birthDay;
    private String email;
    private String phone;
    private ERole role;
    private String specialize; // chuyên môn
    private String qualifications; // trình độ
    private Double hourlyRate;
    private List<Classes> classes;
}
