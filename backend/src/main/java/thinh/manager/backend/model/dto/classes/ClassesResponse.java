package thinh.manager.backend.model.dto.classes;

import thinh.manager.backend.entity.Course;
import thinh.manager.backend.entity.Student;
import thinh.manager.backend.model.dto.classroom.ClassRoomDto;
import thinh.manager.backend.model.dto.course.CourseDto;
import thinh.manager.backend.model.dto.student.StudentDto;
import thinh.manager.backend.model.dto.teacher.TeacherDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassesResponse {
    private String id;
    private String className; // ten lop
    private String description; // mo ta lop
    private String subject; // mon hoc
    private LocalDate timeStart;
    private LocalDate timeEnd;
    private int sessionId;
    private Integer dayId;
    private TeacherDto teacher;
    private ClassRoomDto room;
    private CourseDto course;
    private int numberStudentOfClass;
    private List<StudentDto> studentOfClass;

    public static ClassesResponse classesResponse(ClassesDto classesDto,TeacherDto teacher,ClassRoomDto classRoomDto,CourseDto course,int numberStudentOfClass,List<StudentDto> studentOfClass){
        return ClassesResponse.builder()
                .id(classesDto.getId())
                .className(classRoomDto.getName())
                .description(classesDto.getDescription())
                .subject(classesDto.getSubject())
                .timeStart(classesDto.getTimeStart())
                .timeEnd(classesDto.getTimeEnd())
                .sessionId(classesDto.getSession())
                .dayId(classesDto.getDayOfWeek())
                .teacher(teacher)
                .room(classRoomDto)
                .course(course)
                .numberStudentOfClass(numberStudentOfClass)
                .studentOfClass(studentOfClass)
                .build();
    }

}
