package thinh.manager.backend.model.dto.student;

import thinh.manager.backend.entity.Classes;
import thinh.manager.backend.entity.Student;
import thinh.manager.backend.model.dto.classes.ClassesDto;
import thinh.manager.backend.model.dto.classes.ClassesResponse;
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
}
