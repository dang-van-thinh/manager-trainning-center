package thinh.manager.backend.model.dto.course;

import thinh.manager.backend.entity.Course;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseDto {
    private String id;
    @NotNull(message = "Không được để trống trường này !")
    private String name;
    @NotNull(message = "Không được để trống trường này !")
    private Double price;

    public static CourseDto courseDto(Course course){
        return CourseDto.builder()
                .id(course.getId())
                .name(course.getName())
                .price(course.getPrice())
                .build();
    }
}
