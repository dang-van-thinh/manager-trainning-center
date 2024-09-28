package thinh.manager.backend.model.dto.course;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateCourseDto {
    private String name;
    private Double price;
    private Integer dayToComplete;
    private LocalDate startDate;
    private LocalDate endDate;
}
