package thinh.manager.backend.model.dto.income;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class IncomeResponse {
    private String id;
    private String nameCourse;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalAmount;
}
