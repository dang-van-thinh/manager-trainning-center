package thinh.manager.backend.model.dto.schedule;

import thinh.manager.backend.entity.Schedule;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleDto {
    private String id;

    @NotNull(message = "Không được để trống trường này !")
    private String classesID;
    @NotNull(message = "Không được để trống trường này !")
    private String teacher;

    public static ScheduleDto toScheduleDto(Schedule schedule){
        return ScheduleDto.builder()
                .id(schedule.getId())
                .classesID(schedule.getClasses())
                .build();
    }
}
