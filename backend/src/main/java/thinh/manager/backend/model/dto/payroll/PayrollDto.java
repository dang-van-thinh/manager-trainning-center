package thinh.manager.backend.model.dto.payroll;

import thinh.manager.backend.entity.PayRoll;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PayrollDto {
    private String id;
    private String teacher;
    private double totalHours;
    private double totalSalary;
    private LocalDate payDateStart;
    private LocalDate payDateEnd;

    public static PayrollDto payrollDto (PayRoll payRoll){
        return PayrollDto.builder()
                .id(payRoll.getId())
                .teacher(payRoll.getTeacher())
                .payDateStart(payRoll.getPayDateStart())
                .payDateEnd(payRoll.getPayDateEnd())
                .totalHours(payRoll.getTotalHours())
                .totalSalary(payRoll.getTotalSalary())
                .build();
    }
}
