package thinh.manager.backend.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PayRollDto {
    private String id;

    @NotNull(message = "Không được để trống trường này !")
    private String teacher;
    @NotNull(message = "Không được để trống trường này !")

    private double totalHours;
    @NotNull(message = "Không được để trống trường này !")

    private double totalSalary;
    @NotNull(message = "Không được để trống trường này !")

    private LocalDate payDateStart;
    @NotNull(message = "Không được để trống trường này !")

    private LocalDate payDateEnd;
}
