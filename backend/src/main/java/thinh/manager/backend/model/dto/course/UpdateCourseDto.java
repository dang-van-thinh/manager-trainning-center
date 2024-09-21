package thinh.manager.backend.model.dto.course;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCourseDto {
    @NotNull(message = "Không được để trống trường này !")
    private String name;
    @NotNull(message = "Không được để trống trường này !")
    private double price;

}
