package thinh.manager.backend.model.response.enrollment;

import lombok.Getter;
import lombok.Setter;
import thinh.manager.backend.entity.Enrollment;
import thinh.manager.backend.entity.Student;

@Getter
@Setter
public class EnrollmentStudent {
    private Enrollment enrollment;
    private Student student;

    public EnrollmentStudent(Enrollment enrollment, Student student) {
        this.enrollment = enrollment;
        this.student = student;
    }
}
