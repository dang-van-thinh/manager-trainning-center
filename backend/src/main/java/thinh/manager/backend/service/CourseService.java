package thinh.manager.backend.service;

import thinh.manager.backend.entity.Classes;
import thinh.manager.backend.entity.Course;
import thinh.manager.backend.model.dto.course.CourseDto;
import thinh.manager.backend.model.dto.course.CourseResponse;
import thinh.manager.backend.model.dto.course.UpdateCourseDto;
import thinh.manager.backend.model.dto.dtoMore.CourseClassesDto;
import thinh.manager.backend.repository.ClassesRepository;
import thinh.manager.backend.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseService {
    private final CourseRepository repository;
    private final ClassesRepository classesRepository;


    @Autowired
    public CourseService(
            CourseRepository repository,
            ClassesRepository classesRepository
    ) {
        this.repository = repository;
        this.classesRepository = classesRepository;
    }

    public boolean isStudentInCourse(String studentId,String courseId){
        Optional<Course> course = repository.findCourseByStudentIdAndCourseId(studentId,courseId);
        if (course.isPresent()){
            return true;
        }
        return false;
    }

    public CourseClassesDto getCourseByClassId(String classId){
        Optional<CourseClassesDto> courseClassesDto = repository.findCourseByClass(classId);
        if (courseClassesDto.isEmpty()){
            throw new NullPointerException("Không tìm thấy khóa học phù hợp !");
        }
        return courseClassesDto.get();
    }

    public CourseDto store(CourseDto courseDto) {
        Course course = Course.builder()
                .name(courseDto.getName())
                .price(courseDto.getPrice())
                .build();

        return CourseDto.courseDto(repository.save(course));
    }

    public List<CourseDto> getAllCourseDto(){
        return repository.findAll().stream().map(CourseDto::courseDto).toList();
    }

    public List<CourseResponse> getAll() {
        List<Classes> classesList = classesRepository.findAll();

        List<Course> courseList = repository.findAll();

        List<CourseResponse> courseResponses = new ArrayList<>();


        for (Course course : courseList) {
            List<Classes> classesResponse = new ArrayList<>();
            for (Classes classes : classesList) {
                if (course.getId().equals(classes.getCourse())) {
                    classesResponse.add(classes);
                }
            }

            courseResponses.add(CourseResponse.builder()
                            .id(course.getId())
                            .name(course.getName())
                            .price(course.getPrice())
                            .numberClassOfCourse(classesResponse.size())
                    .build());
        }

        return courseResponses;
    }

    public CourseResponse getOne(String id) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new NullPointerException("Không tìm thấy khóa học phù hợp !"));

        List<Classes> classesOfCourse = classesRepository.getAllByCourseIs(id);

        return CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .price(course.getPrice())
                .classes(classesOfCourse)
                .build();
    }

    public Course getCourse(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NullPointerException("Không tìm thấy khóa học phù hợp !"));
    }

    public void delete(String id) {
        repository.delete(this.getCourse(id));
    }

    public CourseDto update(String id, UpdateCourseDto request) {
        Course coursed = this.getCourse(id);

        coursed.setName(request.getName());
        coursed.setPrice(request.getPrice());

        return CourseDto.courseDto(repository.save(coursed));
    }


}
