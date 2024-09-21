package thinh.manager.backend.model.dto.classroom;

import thinh.manager.backend.entity.ClassRoom;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassRoomDto {
    private String id;
    @NotNull(message = "Không được để trống trường này !")
    private String name;
    @NotNull(message = "Không được để trống trường này !")
    private String location;
    @NotNull(message = "Không được để trống trường này !")
    private Integer capacity; //(dung tích) số lượng học viên có thể chứa trong phòng

    public static ClassRoomDto toClassRoomDto(ClassRoom classRoom) {
        return ClassRoomDto.builder()
                .id(classRoom.getId())
                .capacity(classRoom.getCapacity())
                .location(classRoom.getLocation())
                .name(classRoom.getLocation())
                .build();
    }
}
