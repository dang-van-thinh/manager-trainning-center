package thinh.manager.backend.model.dto.dtoMore;

import thinh.manager.backend.entity.Classes;
import thinh.manager.backend.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseClassesDto {
    private Classes classes;
    private Course course;
}
