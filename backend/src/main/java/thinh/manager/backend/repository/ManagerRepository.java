package thinh.manager.backend.repository;

import thinh.manager.backend.entity.Mananger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Mananger,String> {
    Optional<Mananger> findByEmailAndAndPassword(String email, String password);
}
