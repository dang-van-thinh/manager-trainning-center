package thinh.manager.backend.controller;

import thinh.manager.backend.model.dto.teacher.TeacherDto;
import thinh.manager.backend.model.dto.teacher.UpdateTeacherDto;
import thinh.manager.backend.service.TeacherService;
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
@Tag(name = "Teacher Management", description = "Quản lý giáo viên")
@RequestMapping("/api/teachers")
public class TeacherController {
    @Autowired
    private TeacherService service;

    @Operation(summary = "Thêm mới giáo viên", description = "Thêm mới thông tin giáo viên")
    @PostMapping
    public ResponseEntity<?> store(@RequestBody @Valid TeacherDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.store(request));
    }


    @Operation(summary = "Lấy danh sách giáo viên", description = "Trả ra danh sách giáo viên")
    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @Operation(summary = "Lấy danh sách giáo viên  ", description = "Lấy danh sách giáo viên theo các tiêu chí tên , giới tính , ngày sinh , chuyên môn, lớp dạy ")
    @GetMapping("/search")
    public ResponseEntity<List<TeacherDto>> filterTeacher(
            @RequestParam(value = "full-name", required = false) String fullName,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "start-date", required = false) LocalDate startDate,
            @RequestParam(value = "end-date", required = false) LocalDate endDate,
            @RequestParam(value = "specialize", required = false) String specialize,
            @RequestParam(value = "class", required = false) String classId

    ) {
        return ResponseEntity.ok(service.searchTeacher(fullName, gender, startDate, endDate, specialize, classId));
    }


    @Operation(summary = "Lấy thông tin chi tiết của giáo viên", description = "Trả ra thông tin chi tiết của 1 giáo viên")
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getOne(@PathVariable String id) {
        return ResponseEntity.ok(service.getTeacherDto(id));
    }

    @Operation(summary = "Xóa thông tin 1 giáo viên", description = "Xóa thôn tin giáo viên")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws BadRequestException {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Xóa thành công tài khoản giáo viên !");
    }

    @Operation(summary = "Cập nhật thông tin giáo viên", description = "Cập nhật thông tin giáo viên")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateTeacherDto request) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, request));
    }
}
