package thinh.manager.backend.service;

import thinh.manager.backend.entity.Student;
import thinh.manager.backend.model.dto.classes.ClassesDto;
import thinh.manager.backend.model.dto.enrollment.EnrollmentDto;
import thinh.manager.backend.model.dto.student.StudentDto;
import thinh.manager.backend.model.dto.student.StudentResponse;
import thinh.manager.backend.model.dto.student.UpdateStudentDto;
import thinh.manager.backend.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StudentService {
    private final StudentRepository repo;
    private final ClassesService classesService;
    private final EnrollmentService enrollmentService;

    @Autowired
    public StudentService(
            StudentRepository studentRepository,
            @Lazy ClassesService classesService,
            @Lazy EnrollmentService enrollmentService
    ) {
        this.repo = studentRepository;
        this.classesService = classesService;
        this.enrollmentService = enrollmentService;
    }

    public StudentDto storeStudent(StudentDto studentDto) {
        Student studentCreate = Student.builder()
                .fullName(studentDto.getFullName())
                .birthDay(studentDto.getBirthDay())
                .gender(studentDto.getGender())
                .email(studentDto.getEmail())
                .phone(studentDto.getPhone())
                .level(studentDto.getLevel())
                .build();

        return StudentDto.toStudentDto(repo.save(studentCreate));
    }

    public List<StudentDto> getAll() {
        return repo.findAll().stream().map(StudentDto::toStudentDto).toList();
    }

    public StudentDto updateStudent(String id, UpdateStudentDto studentDto) {
        Student student = this.getStudent(id);

        if (studentDto.getGender() != null) {
            student.setGender(studentDto.getGender());
        }
        if (studentDto.getPhone() != null) {
            student.setPhone(studentDto.getPhone());
        }
        if (studentDto.getFullName() != null) {
            student.setFullName(studentDto.getFullName());
        }
        if (studentDto.getEmail() != null) {
            student.setEmail(studentDto.getEmail());
        }
        if (studentDto.getBirthDay() != null) {
            student.setBirthDay(studentDto.getBirthDay());
        }
        if (studentDto.getLevel() != null) {
            student.setLevel(studentDto.getLevel());
        }
        return StudentDto.toStudentDto(repo.save(student));
    }

    public Student getStudent(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new NullPointerException("Không tìm thấy học viên phù hợp !"));
    }

    // lay danh sach hoc vien theo lop
    public List<StudentDto> getAllStudentByClassesID(String classesID) {
        return repo.getAllStudentByClassesID(classesID).stream().map(StudentDto::toStudentDto).toList();
    }

    // xoa
    public void delete(String id) {
        repo.deleteById(id);
    }

    // tim kiem
    List<Student> filterStudent(String name, LocalDate startDate, LocalDate endDate, String gender, String classId, String courseId) {
        return repo.filterStudent(name, startDate, endDate, gender);
    }

    // tim kiem hoc vien
    public List<StudentResponse> searchStudent(String name, LocalDate startDate, LocalDate endDate, String gender, String classId, String courseId) {
        List<Student> studentList = this.filterStudent(name, startDate, endDate, gender, classId, courseId);
        List<EnrollmentDto> enrollments = enrollmentService.getAll();
        List<ClassesDto> classesDtoList = classesService.findAllClassesDto();
        List<StudentResponse> studentResponses = new ArrayList<>();

        studentList.forEach(student -> {
            List<EnrollmentDto> enrollmentDtoList = new ArrayList<>();
            List<ClassesDto> classesDtoResponse = new ArrayList<>();
            // tim tat ca id lop hoc theo id cua hoc vien
            enrollments.forEach(enrollmentDto -> {
                if (student.getId().equals(enrollmentDto.getStudentId())) {
                    enrollmentDtoList.add(enrollmentDto);
                }
            });

            // so sanh id class de lay ra thong tin trong bang class
            classesDtoList.forEach(classesDto -> {
                enrollmentDtoList.forEach(enrollmentDto -> {
                    if (classesDto.getId().equals(enrollmentDto.getClassesId())) {
                        classesDtoResponse.add(classesDto);
                    }
                });
            });

            studentResponses.add(
                    StudentResponse.builder()
                            .id(student.getId())
                            .fullName(student.getFullName())
                            .email(student.getEmail())
                            .phone(student.getPhone())
                            .gender(student.getGender())
                            .birthDay(student.getBirthDay())
                            .classesResponses(classesDtoResponse)
                            .build()
            );
        });
        return studentResponses;
    }
}
