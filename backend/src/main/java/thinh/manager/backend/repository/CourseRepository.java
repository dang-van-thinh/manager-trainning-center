package thinh.manager.backend.repository;

import thinh.manager.backend.entity.Course;
import thinh.manager.backend.model.dto.dtoMore.CourseClassesDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,String> {
    @Query("select co from Course co , Student s,Enrollment e,Classes c " +
            "where s.id = e.studentId " +
            "and c.id = e.classesId " +
            "and co.id = c.course " +
            "and co.id = :courseId " +
            "and s.id = :studentId")
    Optional<Course> findCourseByStudentIdAndCourseId(@Param("studentId") String studentId,@Param("courseId") String courseId);

    @Query("select new thinh.manager.backend.model.dto.dtoMore.CourseClassesDto(c,co) from Classes c , Course co " +
            "where co.id = c.course " +
            "and c.id = :classId ")
    Optional<CourseClassesDto> findCourseByClass(@Param("classId") String classId);
}
