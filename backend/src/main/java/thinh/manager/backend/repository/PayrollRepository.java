package thinh.manager.backend.repository;

import thinh.manager.backend.entity.PayRoll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollRepository extends JpaRepository<PayRoll,String> {
}
