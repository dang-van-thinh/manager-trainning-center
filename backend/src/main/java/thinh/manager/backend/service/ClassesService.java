package thinh.manager.backend.service;

import thinh.manager.backend.entity.*;
import thinh.manager.backend.model.dto.classes.ClassesDto;
import thinh.manager.backend.model.dto.classes.ClassesResponse;
import thinh.manager.backend.model.dto.classes.UpdateClassesDto;
import thinh.manager.backend.model.dto.classroom.ClassRoomDto;
import thinh.manager.backend.model.dto.course.CourseDto;
import thinh.manager.backend.model.dto.schedule.ScheduleDto;
import thinh.manager.backend.model.dto.student.StudentDto;
import thinh.manager.backend.model.dto.teacher.TeacherDto;
import thinh.manager.backend.repository.ClassesRepository;
import thinh.manager.backend.repository.EnrollmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClassesService {
    private final ClassesRepository repository;
    private final ClassRoomService classRoomService;
    private final TeacherService teacherService;
    private final ScheduleService scheduleService;
    private final EnrollmentRepository enrollmentRepository;
    private final CourseService courseService;
private final StudentService studentService;

    @Autowired
    public ClassesService(
            ClassesRepository repository,
            ClassRoomService classRoomService,
            TeacherService teacherService,
            ScheduleService scheduleService,
            CourseService courseService,
            EnrollmentRepository enrollmentRepository,
           @Lazy StudentService studentService

    ) {
        this.repository = repository;
        this.classRoomService = classRoomService;
        this.teacherService = teacherService;
        this.scheduleService = scheduleService;
        this.courseService = courseService;
        this.enrollmentRepository = enrollmentRepository;
        this.studentService = studentService;
    }

    public List<ClassesDto> findAllClassesDto(){
     return repository.findAll().stream().map(ClassesDto::toClassesDto).toList();
    }

    public List<ClassesResponse> getAll() {
        List<Classes> classesList = repository.findAll();
        List<TeacherDto> teacherDtoList = teacherService.getAll();
        List<CourseDto> courseDtoList = courseService.getAllCourseDto();
        List<ClassRoomDto> classRoomDtoList = classRoomService.getAll();
        List<Enrollment> enrollmentList = enrollmentRepository.findAll();
        List<ClassesResponse> classesResponses = new ArrayList<>();

        for (Classes classes : classesList) {
            ClassesResponse classesResponse = new ClassesResponse();
            classesResponse.setId(classes.getId());
            classesResponse.setClassName(classes.getClassName());
            classesResponse.setSubject(classes.getSubject());
            classesResponse.setTimeStart(classes.getTimeStart());
            classesResponse.setTimeEnd(classes.getTimeEnd());
            classesResponse.setDescription(classes.getDescription());
            classesResponse.setSessionId(classes.getSession());
            classesResponse.setDayId(classes.getDayOfWeek());

            for (TeacherDto teacherDto : teacherDtoList) {
                if (classes.getTeacher().equals(teacherDto.getId())) {
                    classesResponse.setTeacher(teacherDto);
                }
            }

            for (CourseDto courseDto : courseDtoList
            ) {
                if (courseDto.getId().equals(classes.getCourse())) {
                    classesResponse.setCourse(courseDto);
                }
            }

            for (ClassRoomDto classRoomDto : classRoomDtoList
            ) {
                if (classRoomDto.getId().equals(classes.getClassRoom())) {
                    classesResponse.setRoom(classRoomDto);
                }
            }

            List<Enrollment> enrollmentsResponse = new ArrayList<>();
            for (Enrollment enrollment: enrollmentList
                 ) {
                if (enrollment.getClassesId().equals(classes.getId())){
                    enrollmentsResponse.add(enrollment);
                }
            }

            classesResponse.setNumberStudentOfClass(enrollmentsResponse.size());

            classesResponses.add(classesResponse);
        }

        return classesResponses;

    }


    @Transactional(rollbackFor = BadRequestException.class)
    public ClassesDto store(ClassesDto request) throws BadRequestException {
        String teacherId = teacherService.getTeacher(request.getTeacherId()).getId();
        String classRoomId = classRoomService.getRoom(request.getClassroomId()).getId();

        // them lich day cho giao vien
        // check trung lịch
        if (isDuplicateRoom(request.getClassroomId(),request.getSession(),request.getDayOfWeek())){
            throw new BadRequestException("Lớp học bị trùng thời gian !");
        }

        Classes classes = Classes.builder()
                .subject(request.getSubject())
                .description(request.getDescription())
                .classRoom(classRoomId)
                .teacher(teacherId)
                .dayOfWeek(request.getDayOfWeek())
                .timeEnd(request.getTimeEnd())
                .timeStart(request.getTimeStart())
                .className(request.getClassName())
                .session(request.getSession())
                .course(request.getCourse())
                .build();
        Classes classed = repository.save(classes);


        if (!scheduleService.isDuplicateSchedule(request.getTeacherId(), request.getSession(), request.getDayOfWeek())) {
            scheduleService.create(ScheduleDto.builder()
                    .classesID(classed.getId())
                    .teacher(classed.getTeacher())
                    .build());
            return ClassesDto.toClassesDto(classed);

        } else throw new BadRequestException("Trùng lịch dạy của giáo viên !");

    }

    private boolean isDuplicateRoom(String roomId, Integer sessionId, Integer dayId){
        Optional<Classes> classes = repository.findBySessionIsAndDayOfWeekIsAndClassRoomIs(sessionId,dayId,roomId);

        if (!classes.isEmpty()){
            return true;
            
        }
        return false;
    }

    public ClassesResponse getOneClass(String classId) throws BadRequestException {
        ClassesDto classesDto = ClassesDto.toClassesDto(this.getClasses(classId));
        List<StudentDto> studentDtoList = studentService.getAllStudentByClassesID(classesDto.getId()); ;

        TeacherDto teacherDto = teacherService.getTeacherDto(classesDto.getTeacherId());

        ClassRoomDto classRoomDto = classRoomService.getRoomDto(classesDto.getClassroomId());

        CourseDto courseDto =CourseDto.courseDto( courseService.getCourse(classesDto.getCourse()));

        List<Enrollment> numberStudentOfClass = enrollmentRepository.findAllByClassesIdIs(classesDto.getId());
        return ClassesResponse.classesResponse(classesDto, teacherDto, classRoomDto, courseDto, numberStudentOfClass.size(),studentDtoList);

    }

    public Classes getClasses(String id) {
        Optional<Classes> classes = repository.findById(id);

        if (classes.isEmpty()) {
            throw new NullPointerException("Không tìm thấy lớp học phù hợp !");
        }
        return classes.get();
    }

    // lay tat ca lop hoc ma hoc vien dang theo hoc
    public List<Classes> getAllClassByStudent(String studentID) {
        return repository.getAllClassesByStudent(studentID);
    }

    public ClassesDto updateClass(String id, UpdateClassesDto classesDto){
        Classes classes = this.getClasses(id);
        ClassRoom classRoom = classRoomService.getRoom(classesDto.getClassroom_id());
        Teacher teacher = teacherService.getTeacher(classesDto.getTeacher_id());

        classes.setClassName(classesDto.getClassName());
        classes.setClassRoom(classRoom.getId());
        classes.setSubject(classesDto.getSubject());
        classes.setTeacher(teacher.getId());
        classes.setDescription(classesDto.getDescription());
        classes.setTimeEnd(classesDto.getTimeEnd());
        classes.setTimeStart(classesDto.getTimeStart());


        return ClassesDto.toClassesDto(repository.save(classes));
    }

    public void deleteClass(String id){
        repository.delete(this.getClasses(id));
    }

    public boolean isStudentBySessionOfDay(String studentId,Integer sessionId,Integer dayId){
        Optional<Classes> classes = repository.findClassOfStudentBySessionOfDay(studentId,sessionId,dayId);

        if (classes.isPresent()){
            return true;
        }
        return false;
    }


}
