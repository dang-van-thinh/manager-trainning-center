package thinh.manager.backend.service;

import thinh.manager.backend.entity.Student;
import thinh.manager.backend.model.dto.classes.ClassesDto;
import thinh.manager.backend.model.dto.enrollment.EnrollmentDto;
import thinh.manager.backend.model.dto.student.CreateStudentRequest;
import thinh.manager.backend.model.dto.student.StudentDto;
import thinh.manager.backend.model.dto.student.StudentResponse;
import thinh.manager.backend.model.dto.student.UpdateStudentRequest;
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
    private final MailService mailService;

    @Autowired
    public StudentService(
            StudentRepository studentRepository,
            @Lazy ClassesService classesService,
            @Lazy EnrollmentService enrollmentService,
            MailService mailService
    ) {
        this.repo = studentRepository;
        this.classesService = classesService;
        this.enrollmentService = enrollmentService;
        this.mailService=mailService;
    }

    public StudentDto storeStudent(CreateStudentRequest request) {
        Student studentCreate = Student.builder()
                .fullName(request.getFullName())
                .birthDay(request.getBirthDay())
                .gender(request.getGender())
                .email(request.getEmail())
                .phone(request.getPhone())
                .level(request.getLevel())
                .active(false)
                .address(request.getAddress())
                .build();
        Student student = repo.save(studentCreate);

        mailService.sendEmailVerifyEmailRegister(student.getEmail(),student.getFullName(),student.getId());
        return StudentDto.toStudentDto(student);
    }

    public StudentDto verifyStudent(String id){
        Student student = getStudent(id);
        student.setActive(true);
        return StudentDto.toStudentDto(repo.save(student));
    }

    public List<StudentDto> getAll() {
        return repo.findAll().stream().map(StudentDto::toStudentDto).toList();
    }

    public StudentDto updateStudent(String id, UpdateStudentRequest request) {
        Student student = this.getStudent(id);

        if (request.getGender() != null) {
            student.setGender(request.getGender());
        }
        if (request.getPhone() != null) {
            student.setPhone(request.getPhone());
        }
        if (request.getFullName() != null) {
            student.setFullName(request.getFullName());
        }
        if (request.getEmail() != null) {
            student.setEmail(request.getEmail());
        }
        if (request.getBirthDay() != null) {
            student.setBirthDay(request.getBirthDay());
        }
        if (request.getLevel() != null) {
            student.setLevel(request.getLevel());
        }
        if (request.getAddress() != null){
            student.setAddress(request.getAddress());
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

    // tim kiem hoc vien theo ten , khoang ngay sinh  , gioi tinh
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
