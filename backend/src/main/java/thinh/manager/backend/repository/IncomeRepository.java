package thinh.manager.backend.repository;

import thinh.manager.backend.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income,String> {
    @Query(value = "select sum(i.amount) as totalAmount from income i where i.date between :startDate and :endDate",nativeQuery = true)
    Double toTalIncome(LocalDate startDate, LocalDate endDate);

    Optional<Income> findByStudentIdIsAndCoursesIdIs(String studentId, String courseId);
}
