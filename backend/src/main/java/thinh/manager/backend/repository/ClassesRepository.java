package thinh.manager.backend.repository;

import thinh.manager.backend.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassesRepository extends JpaRepository<Classes,String> {

    @Query(value = "select c.* from classes c join enrollment e on c.id = e.classes_id where e.student_id = :studentID",nativeQuery = true)
    List<Classes> getAllClassesByStudent(@Param("studentID") String studentID);

    List<Classes> getAllByCourseIs(String courseId);

    Optional<Classes> findBySessionIsAndDayOfWeekIsAndClassRoomIs(Integer sessionId, Integer dayId, String roomId);

    @Query("select c from Classes c , Student s, Enrollment e " +
            "where c.id = e.classesId " +
            "and s.id = e.studentId " +
            "and s.id = :studentId " +
            "and c.session = :sessionId " +
            "and c.dayOfWeek = :dayId")
    Optional<Classes> findClassOfStudentBySessionOfDay(@Param("studentId") String studentId ,
                                               @Param("sessionId") Integer sessionId,
                                               @Param("dayId") Integer dayId);

}
