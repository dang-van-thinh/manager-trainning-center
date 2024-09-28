package thinh.manager.backend.model.dto.course;

import thinh.manager.backend.entity.Classes;
import thinh.manager.backend.model.dto.classes.ClassesDto;
import thinh.manager.backend.model.dto.classes.ClassesResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class CourseResponse {
    private String id;
    private String name;
    private Double price;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer daysToComplete;
    private Integer numberClassOfCourse;
    private List<Classes> classes;
}
