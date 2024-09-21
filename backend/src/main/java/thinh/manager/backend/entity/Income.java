package thinh.manager.backend.entity;

import thinh.manager.backend.entity.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private double amount;
    private String title;
    private String description;
    private LocalDate date;
    @Column(name = "student_id")
    private String studentId;
    @Column(name = "received_from")
    private String receivedFrom; // nhan tu ai
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod; // phuong thuc thanh toan
    private String note;
    private String coursesId;
}
