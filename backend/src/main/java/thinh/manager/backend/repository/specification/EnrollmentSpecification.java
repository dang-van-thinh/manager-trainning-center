package thinh.manager.backend.repository.specification;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import thinh.manager.backend.entity.Enrollment;
import thinh.manager.backend.entity.Student;
import thinh.manager.backend.model.response.enrollment.EnrollmentStudent;

import java.util.Objects;

public class EnrollmentSpecification {
    public static Specification<Enrollment> hasStudentId(String studentId){
        return (root, query, builder) -> builder.equal(root.get("studentId"),studentId);
    }
    public static Specification<Enrollment> joinEnrollment(){
//        return (root, query, builder) ->{
//            Join<Enrollment,Student> enrollmentJoin = root.join("studentId", JoinType.INNER);
//            query.multiselect(root,enrollmentJoin);
//            return builder.conjunction();
//        };

        return (root, query, builder) -> {
            // Tạo subquery cho bảng Student
            Subquery<Student> studentSubquery = query.subquery(Student.class);
            Root<Student> studentRoot = studentSubquery.from(Student.class);

            // Điều kiện join dựa trên giá trị tham chiếu studentId
            studentSubquery.select(studentRoot)
                    .where(builder.equal(root.get("studentId"), studentRoot.get("id")));

            // Áp dụng subquery vào query chính
            query.where(builder.exists(studentSubquery));

            // Trả về kết quả hợp lệ
            return builder.conjunction();
        };
    }
}
