package thinh.manager.backend.controller;

import thinh.manager.backend.model.dto.student.CreateStudentRequest;
import thinh.manager.backend.model.dto.student.StudentDto;
import thinh.manager.backend.model.dto.student.StudentResponse;
import thinh.manager.backend.model.dto.student.UpdateStudentRequest;
import thinh.manager.backend.service.EnrollmentService;
import thinh.manager.backend.service.MailService;
import thinh.manager.backend.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Management", description = "Quản lý học viên")
public class StudentController {
    private final StudentService service;
    private final EnrollmentService enrollmentService;
    private MailService mailService;

    @Autowired
    public StudentController(
            StudentService service,
            EnrollmentService enrollmentService,
            MailService mailService
    ) {
        this.service = service;
        this.enrollmentService = enrollmentService;
        this.mailService = mailService;
    }

    @Operation(description = "Trả ra danh sách học viên !", summary = "Lấy danh sách tất cả học viên ")
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Tạo mới học viên", description = "Trả ra học viên vừa mới thêm !")
    @PostMapping
    public ResponseEntity<?> store(@RequestBody @Valid CreateStudentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.storeStudent(request));
    }

    @Operation(summary = "Lấy thông tin của 1 học viên", description = "Lấy thông tin 1 học viên ")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id) {
        return ResponseEntity.ok(enrollmentService.getOneStudent(id));
    }

    @Operation(summary = "Xóa thông tin của 1 học viên", description = "Xóa thông tin 1 học viên ")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Xoá thành công rồi nhé !");
    }

    @Operation(summary = "Cập nhật thông tin học viên", description = "Cập nhật thông tin chi tiết 1 học viên")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateStudentRequest request) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.updateStudent(id, request));
    }

    @Operation(description = "Trả ra danh sách học viên tìm kiếm !", summary = "Lấy tất cả học viên tìm kiếm theo tên , ngày sinh , giới tính , lớp học , khóa học , ngày sinh")
    @GetMapping("/search")
    public ResponseEntity<List<StudentResponse>> searchStudent(
            @RequestParam(required = false, value = "name") String name,
            @RequestParam(required = false , value = "gender") String gender,
            @RequestParam(required = false, value = "start-date") LocalDate startDate,
            @RequestParam(required = false, value = "end-date") LocalDate endDate,
            @RequestParam(required = false, value = "class") String classId,
            @RequestParam(required = false, value = "course") String courseId
    ) {
        return ResponseEntity.ok(service.searchStudent(name, startDate, endDate ,gender,classId,courseId));
    }

//    @GetMapping("/test-sendmail")
//    public void testSendMail(){
//        mailService.sendEmailVerifyEmailRegister();
//    }
}
