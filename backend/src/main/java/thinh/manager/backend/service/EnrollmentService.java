package thinh.manager.backend.service;

import jakarta.persistence.criteria.Predicate;
import thinh.manager.backend.entity.*;
import thinh.manager.backend.entity.enums.PaymentMethod;
import thinh.manager.backend.model.dto.classes.ClassesDto;
import thinh.manager.backend.model.dto.classes.ClassesResponse;
import thinh.manager.backend.model.dto.classroom.ClassRoomDto;
import thinh.manager.backend.model.dto.dtoMore.CourseClassesDto;
import thinh.manager.backend.model.dto.enrollment.EnrollmentDto;
import thinh.manager.backend.model.dto.enrollment.UpdateEnrollment;
import thinh.manager.backend.model.dto.income.IncomeDto;
import thinh.manager.backend.model.dto.student.StudentDto;
import thinh.manager.backend.model.dto.student.StudentResponse;
import thinh.manager.backend.model.dto.teacher.TeacherDto;
import thinh.manager.backend.repository.EnrollmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
@Slf4j
public class EnrollmentService {
    private final StudentService studentService;
    private final ClassesService classesService;
    private final EnrollmentRepository repository;
    private final CourseService courseService;
    private final IncomeService incomeService;
    private final TeacherService teacherService;
    private final ClassRoomService classRoomService;
    private final double serviceFee = 25000; // phi chuyen lop tinh theo ngay

    @Autowired
    public EnrollmentService(
            @Lazy StudentService studentService,
            EnrollmentRepository repository,
            ClassesService classesService,
            CourseService courseService,
            IncomeService incomeService,
            TeacherService teacherService,
            ClassRoomService classRoomService
    ) {
        this.repository = repository;
        this.studentService = studentService;
        this.classesService = classesService;
        this.courseService = courseService;
        this.incomeService = incomeService;
        this.teacherService = teacherService;
        this.classRoomService = classRoomService;
    }

    @Transactional(rollbackFor = BadRequestException.class)
    public EnrollmentDto create(EnrollmentDto request) throws BadRequestException {
        Classes classes = classesService.getClasses(request.getClassesId());
        Student student = studentService.getStudent(request.getStudentId());
        Course course = courseService.getCourse(classes.getCourse());

        // kiem tra xem hoc vien da co trong khoa chua
        if (courseService.isStudentInCourse(student.getId(), classes.getCourse())) {
            throw new BadRequestException("Học viên đã tồn tại trong khóa học !");
        }

        // check hoc vien co bị trung lich hoc ko
        if (classesService.isStudentBySessionOfDay(student.getId(), classes.getSession(), classes.getDayOfWeek())) {
            throw new BadRequestException("Học viên bị trùng lịch học !");
        }

        // them hoa don cho hoc vien
        incomeService.create(IncomeDto.builder()
                .amount(course.getPrice())
                .title("Học phí của học viên")
                .description("Tiền học của học viên " + student.getFullName())
                .date(LocalDate.now())
                .studentId(student.getId())
                .receivedFrom(student.getFullName())
                .paymentMethod(PaymentMethod.BANK_TRANSFER)
                .note("Học viên thanh toán tiền đăng ký khóa học " + course.getName())
                .courseId(course.getId())
                .build());
        // them hoc vien vao lop
        Enrollment enrollment = Enrollment.builder()
                .classesId(classes.getId())
                .studentId(student.getId())
                .timeJoin(LocalDate.now())
                .build();
        return EnrollmentDto.enrollmentDto(repository.save(enrollment));
    }


    public StudentResponse getOneStudent(String id) {
        StudentDto studentDto = StudentDto.toStudentDto(studentService.getStudent(id));

        List<Enrollment> enrollmentListOfStudent = repository.findAllByStudentIdIs(studentDto.getId());
        List<ClassesDto> classesDtoList = classesService.findAllClassesDto();

        List<ClassesDto> classesDtosResponse = new ArrayList<>();
        for (Enrollment enrollment : enrollmentListOfStudent) {
            for (ClassesDto classesDto : classesDtoList) {
                if (enrollment.getClassesId().equals(classesDto.getId())) {
                    classesDtosResponse.add(classesDto);
                }
            }
        }
        return StudentResponse.builder()
                .id(studentDto.getId())
                .fullName(studentDto.getFullName())
                .email(studentDto.getEmail())
                .phone(studentDto.getPhone())
                .gender(studentDto.getGender())
                .birthDay(studentDto.getBirthDay())
                .classesResponses(classesDtosResponse)
                .build();
    }

    @Transactional
    public void delete(EnrollmentDto enrollmentDto) {
        Optional<Enrollment> enrollment = repository.findByClassesIdIsAndStudentIdIs(enrollmentDto.getClassesId(), enrollmentDto.getStudentId());
        List<ClassesDto> classesDtoList = classesService.findAllClassesDto();
        String courseId = null;

        if (enrollment.isEmpty()) {
            throw new NullPointerException("Không tìm thấy");
        }

        for (ClassesDto classesDto : classesDtoList) {
            if (classesDto.getId().equals(enrollment.get().getClassesId())) {
                courseId = classesDto.getCourse();
            }
        }

        Income income = incomeService.getIncome(enrollment.get().getStudentId(), courseId);

        incomeService.delete(income);
        repository.delete(enrollment.get());

    }

    public List<EnrollmentDto> getAll() {
        return repository.findAll().stream().map(EnrollmentDto::enrollmentDto).toList();
    }

    // lay tat ca hoc vien trong lớp
    public List<StudentDto> getStudentInClass(String classesID) {
        Classes classes = classesService.getClasses(classesID);
        return studentService.getAllStudentByClassesID(classes.getId());
    }

    // lay danh sach lop theo hoc vien
    public List<ClassesResponse> getAllClassesByStudent(String studentID) {
        Student student = studentService.getStudent(studentID);
        List<Classes> classes = classesService.getAllClassByStudent(student.getId());
        List<TeacherDto> teacherList = teacherService.getAll();
        List<ClassRoomDto> classRoomList = classRoomService.getAll();
        List<ClassesResponse> classesResponses = new ArrayList<>();

        for (Classes classes1 : classes) {
            TeacherDto teacherDto = new TeacherDto();
            ClassRoomDto classRoomDto = new ClassRoomDto();
            // ss de lay ra teacher day lop do
            for (TeacherDto teacher : teacherList) {
                if (teacher.getId().equals(classes1.getTeacher())) {
                    teacherDto = teacher;
                }
            }
            //ss de lay ra phong hoc cua lop do
            for (ClassRoomDto room : classRoomList) {
                if (room.getId().equals(classes1.getClassRoom())) {
                    classRoomDto = room;
                }
            }
            // them vao danh sách response
            classesResponses.add(
                    ClassesResponse.builder()
                            .teacher(teacherDto)
                            .room(classRoomDto)
                            .build()
            );
        }

        return classesResponses;
    }

    public EnrollmentDto changeEnrollment(UpdateEnrollment request, String studentId) throws BadRequestException {
        StudentDto studentDto = StudentDto.toStudentDto(studentService.getStudent(studentId));
        CourseClassesDto newCourseClassesDto = courseService.getCourseByClassId(request.getNewClassesId());
        String newCourseId = newCourseClassesDto.getCourse().getId();
        Classes newClasses = newCourseClassesDto.getClasses();

        CourseClassesDto oldCourseClassesDto = courseService.getCourseByClassId(request.getOldClassesId());
        String oldCourseId = oldCourseClassesDto.getCourse().getId();
        Classes oldClasses = oldCourseClassesDto.getClasses();

        // check hoc vien co bị trung lich hoc ko
        if (classesService.isStudentBySessionOfDay(studentId, newClasses.getSession(), newClasses.getDayOfWeek())) {
            throw new BadRequestException("Học viên bị trùng lịch học !");
        }
        Optional<Enrollment> enrollment = repository.findByClassesIdIsAndStudentIdIs(oldClasses.getId(), studentId);

        Enrollment enrollmented = enrollment.get();
        // check trung khoa hoc voi lop cu va moi
        if (newCourseId.equals(oldCourseId)) {
            LocalDate startDay = oldClasses.getTimeStart();
            LocalDate dayNow = LocalDate.now();

            enrollmented.setClassesId(newClasses.getId());

            // tinh toan chenh lech ngay
            Period period = Period.between(startDay, dayNow);

            if (period.getDays() > 7) {
                double totalFee = (period.getDays() - 7) * this.serviceFee;
                // them hoa don cho hoc vien
                incomeService.create(IncomeDto.builder()
                        .amount(totalFee)
                        .title("Phí chuyển lớp")
                        .description("Tiền phí chuyển lớp của học viên " + studentDto.getFullName())
                        .date(LocalDate.now())
                        .studentId(studentDto.getId())
                        .receivedFrom(studentDto.getFullName())
                        .paymentMethod(PaymentMethod.BANK_TRANSFER)
                        .note("Phí chuyển lớp của học viên ")
                        .courseId(oldCourseId)
                        .build());
            }

            log.info("Thời gian chênh lệch " + period.getDays());

            repository.save(enrollmented);
        } else {

        }
        return EnrollmentDto.enrollmentDto(enrollmented);
    }

    public List<Enrollment> specificationTest(String studentId) {
//        Specification<Enrollment> spec = Specification.where(EnrollmentSpecification.joinEnrollment());
//        if (studentId != null) {
//            spec = spec.and(EnrollmentSpecification.hasStudentId(studentId));
//        }
//        log.info("specification is run !");
//        return repository.findAll(spec);

        return repository.findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (studentId != null){
                predicates.add(builder.equal(root.get("studentId") , studentId));
            }

            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        });
    }
}
