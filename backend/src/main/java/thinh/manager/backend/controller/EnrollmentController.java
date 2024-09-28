package thinh.manager.backend.controller;

import thinh.manager.backend.model.dto.classes.ClassesResponse;
import thinh.manager.backend.model.dto.enrollment.EnrollmentDto;
import thinh.manager.backend.model.dto.enrollment.UpdateEnrollment;
import thinh.manager.backend.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Enrollment Management",description = "Quản lý thành viên trong lớp học ")
@RequestMapping("api/enrollments")
public class EnrollmentController {
    private final EnrollmentService service;
    @Autowired
    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(@RequestParam("studentId") String studentId){
        System.out.println(studentId);
        return ResponseEntity.ok(service.specificationTest(studentId));
    }

    @Operation(summary = "Thêm mới thành viên vào lớp",description = "Thêm mới học viên vào lớp !")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid EnrollmentDto enrollmentDto) throws BadRequestException {
        return ResponseEntity.ok(service.create(enrollmentDto));
    }

    @Operation(summary = "Lấy danh sách học viên trong lớp học",description = "Trả ra danh sách học viên trong lớp học")
    @GetMapping("/class/{classesID}")
    public ResponseEntity<?> getAllStudentInClasses(@PathVariable String classesID) {
        return ResponseEntity.ok(service.getStudentInClass(classesID));
    }

    @Operation(summary = "Lấy danh sách lớp học theo id học viên",description = "Trả ra danh sách lớp học theo id học viên")
    @GetMapping("/student/{studentID}")
    public ResponseEntity<List<ClassesResponse>> getAllClassesByStudent(@PathVariable String studentID) {
        return ResponseEntity.ok(service.getAllClassesByStudent(studentID));
    }


    @DeleteMapping
    public ResponseEntity<?> deleteStudentOutClasses(@RequestBody EnrollmentDto enrollmentDto){
        service.delete(enrollmentDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<?> changeEnrollment(@RequestBody UpdateEnrollment request , @PathVariable String studentId) throws BadRequestException {
        return ResponseEntity.ok(service.changeEnrollment(request,studentId));
    }



}
