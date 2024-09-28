package thinh.manager.backend.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import thinh.manager.backend.entity.Course;
import thinh.manager.backend.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,String> , JpaSpecificationExecutor<Enrollment> {
    List<Enrollment> findAllByClassesIdIs(String id);
    @Query(value = "select * from enrollment e where e.student_id = :studentId and e.classes_id = :classesId",nativeQuery = true)
    Optional<Enrollment> findAllByStudent(@Param("studentId") String studentId,@Param("classesId") String classesId);

    List<Enrollment> findAllByStudentIdIs(String studentId);

    Enrollment findByStudentIdIsAndClassesIdIs(String studentId,String classesId);

    Optional<Enrollment> findByClassesIdIsAndStudentIdIs(String classesId,String studentId);

    @Query(value = "select co.id from Enrollment e " +
            "join Student s on e.studentId = s.id " +
            "join Classes c on c.id = e.classesId " +
            "join Course co on co.id = c.course " +
            "where e.studentId = :studentId")
    List<Course> findAllCourseByStudentId(@Param("studentId") String studentId);
}
