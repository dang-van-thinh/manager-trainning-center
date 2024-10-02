package thinh.manager.backend.model.dto.student;

import thinh.manager.backend.model.dto.classes.ClassesDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class StudentResponse {
    private String id;
    private String fullName;
    private String gender;
    private LocalDate birthDay;
    private String email;
    private String phone;
    private List<ClassesDto> classesResponses;
    private String image;
    private String address;
    private String active;
    private String level;
}
