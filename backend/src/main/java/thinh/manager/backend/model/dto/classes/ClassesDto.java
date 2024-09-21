package thinh.manager.backend.model.dto.classes;

import thinh.manager.backend.entity.Classes;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassesDto {
    private String id;
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
    @NotNull(message = "Không được để trống trường này !")
    private String course;

    public static ClassesDto toClassesDto(Classes classes) {
        return ClassesDto.builder()
                .id(classes.getId())
                .className(classes.getClassName())
                .subject(classes.getSubject())
                .description(classes.getDescription())
                .timeEnd(classes.getTimeEnd())
                .timeStart(classes.getTimeStart())
                .classroom_id(classes.getClassRoom())
                .teacher_id(classes.getTeacher())
                .session(classes.getSession())
                .dayOfWeek(classes.getDayOfWeek())
                .course(classes.getCourse())
                .build();
    }
}
