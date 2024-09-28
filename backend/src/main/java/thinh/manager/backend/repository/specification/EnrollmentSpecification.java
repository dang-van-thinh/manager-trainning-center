package thinh.manager.backend.repository.specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import thinh.manager.backend.entity.Enrollment;
import thinh.manager.backend.entity.Student;

public class StudentSpecification {
    public static Specification<Enrollment> joinEnrollment(){
        return (root, query, builder) ->{
            Join<Student, Enrollment> enrollmentJoin = root.join("studentId", JoinType.INNER);
            query.multiselect(root,enrollmentJoin);
            return builder.conjunction();
        };
    }
}
