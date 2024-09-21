package thinh.manager.backend.model.dto.classes;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateClassesDto {
    @NotNull(message = "Không được để trống trường này !")
    private String className; // ten lop
    @NotNull(message = "Không được để trống trường này !")
    private String description; // mo ta lop
    @NotNull(message = "Không được để trống trường này !")
    private String subject; // mon hoc
    @NotNull(message = "Không được để trống trường này !")
    private LocalDate timeStart;
    @NotNull(message = "Không được để trống trường này !")
    private LocalDate timeEnd;
    @NotNull(message = "Không được để trống trường này !")
    private String classroom_id;
    @NotNull(message = "Không được để trống trường này !")
    private String teacher_id;
    @NotNull(message = "Không được để trống trường này !")
    private Integer session;
    @NotNull(message = "Không được để trống trường này !")
    private Integer dayOfWeek;
}
