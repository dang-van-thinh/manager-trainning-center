package thinh.manager.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayRoll {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "teacher_id")
    private String teacher;

    @Column(name = "total_hours")
    private double totalHours;

    @Column(name = "total_salary")
    private double totalSalary;

    @Column(name = "pay_date_start")
    private LocalDate payDateStart;

    @Column(name = "pay_date_end")
    private LocalDate payDateEnd;
}
