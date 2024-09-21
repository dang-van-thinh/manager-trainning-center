package thinh.manager.backend.model.dto.classroom;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateClassRoomDto {
    @NotNull(message = "Không được để trống trường này !")
    private String name;
    @NotNull(message = "Không được để trống trường này !")
    private String location;
    @NotNull(message = "Không được để trống trường này !")
    private Integer capacity; //(dung tích) số lượng học viên có thể chứa trong phòng
}
