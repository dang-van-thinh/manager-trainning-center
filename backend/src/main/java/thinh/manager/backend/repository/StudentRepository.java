package thinh.manager.backend.repository;

import org.springframework.data.domain.Page;
import thinh.manager.backend.entity.Classes;
import thinh.manager.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {


    @Query(value = "select s.* from student s join enrollment e on e.student_id = s.id where e.classes_id = :classesID", nativeQuery = true)
    List<Student> getAllStudentByClassesID(@Param("classesID") String classesID);

    @Query("select s from Student s " +
            "where true " +
            "and (:name is null or s.fullName like %:name%) " +
            "and (:gender is null or s.gender = :gender)" +
            "AND (cast(:startDate as timestamp) IS NULL OR s.birthDay >= :startDate) " +
            "AND (cast(:endDate as timestamp) IS NULL OR s.birthDay <= :endDate) " )
    List<Student> filterStudent(
            @Param("name") String name,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("gender") String gender
    );
    List<Student> findAllByFullNameLikeOrGenderIsOrEmailLike(String fullname, String gender, String email);
}
