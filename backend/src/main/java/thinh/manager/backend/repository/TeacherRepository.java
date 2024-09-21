package thinh.manager.backend.repository;

import thinh.manager.backend.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,String> {

    @Query(value = "select t.* from teacher t " +
            "join schedule s on t.id = s.teacher_id " +
            "join classes c on c.id = s.classes_id " +
            "where (:name is null or t.full_name like %:name% ) " +
            "and (cast(:classId as varchar) is null or c.id = :classId)" +
            "and (cast(:specialize as varchar) is null or t.specialize like %:specialize%) " +
            "and (cast(:gender as varchar) is null  or t.gender = :gender) " +
            "and (cast(:startDate as timestamp ) is null or t.birth_day >= :startDate) " +
            "and (cast(:endDate as timestamp ) is null or t.birth_day <= :endDate ) ",nativeQuery = true)
    List<Teacher> searchTeacher(
            @Param("name") String name,
            @Param("gender") String gender,
            @Param("startDate")LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("specialize") String specialize,
            @Param("classId") String classId
            );
    List<Teacher> findAllByFullNameLikeOrGenderIsOrEmailLike(String name,String gender, String email);
}
