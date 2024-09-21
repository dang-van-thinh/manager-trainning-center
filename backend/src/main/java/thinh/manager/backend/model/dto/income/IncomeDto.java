package thinh.manager.backend.model.dto.income;

import thinh.manager.backend.entity.Income;
import thinh.manager.backend.entity.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class IncomeDto {
    private String id;
    @NotNull(message = "Không được để trống trường này !")
    private double amount;
    @NotNull(message = "Không được để trống trường này !")

    private String title;
    @NotNull(message = "Không được để trống trường này !")

    private String description;
    @NotNull(message = "Không được để trống trường này !")

    private LocalDate date;
    @NotNull(message = "Không được để trống trường này !")

    private String receivedFrom; // nhan tu ai
    @NotNull(message = "Không được để trống trường này !")

    private PaymentMethod paymentMethod; // phuong thuc thanh toan
    @NotNull(message = "Không được để trống trường này !")

    private String note;
    private String courseId;
    private String studentId;

    public static IncomeDto incomeDto (Income income){
        return IncomeDto.builder()
                .amount(income.getAmount())
                .title(income.getTitle())
                .description(income.getDescription())
                .date(income.getDate())
                .receivedFrom(income.getReceivedFrom())
                .paymentMethod(income.getPaymentMethod())
                .note(income.getNote())
                .courseId(income.getCoursesId())
                .studentId(income.getStudentId())
                .build();
    }
}
