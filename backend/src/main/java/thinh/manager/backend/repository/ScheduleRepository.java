package thinh.manager.backend.repository;

import thinh.manager.backend.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,String> {
     @Query(value = "select s.* from schedule s join classes c on s.classes_id = c.id " +
             "where c.teacher_id = :teacherID " +
             "and c.day_id = :dayId " +
             "and c.session_id = :sessionId",nativeQuery = true)
     List<Schedule> findAllByTeacher(@Param("teacherID")  String teacherID, @Param("dayId") Integer dayId,@Param("sessionId") Integer sessionId);

     @Query(value = "select count(c.session_id)  from schedule s join teacher t on s.teacher_id = t.id " +
             "join classes c on s.classes_id = c.id where c.teacher_id = :teacherId",nativeQuery = true)
     Integer countSessionByTeacher(String teacherId);
}
