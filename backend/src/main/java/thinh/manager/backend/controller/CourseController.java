package thinh.manager.backend.controller;

import thinh.manager.backend.model.dto.course.CourseDto;
import thinh.manager.backend.model.dto.course.CourseResponse;
import thinh.manager.backend.model.dto.course.UpdateCourseDto;
import thinh.manager.backend.service.CourseService;
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
@Tag(name = "Course Manager", description = "Quản lý khóa học")
@RequestMapping("api/courses")
public class CourseController {
    @Autowired
    private CourseService service;

    @Operation(summary = "Thêm mới khóa học ",description = "Thêm mới thông tin khóa học")
    @PostMapping
    public ResponseEntity<?> store(@RequestBody @Valid CourseDto courseDto){
        return ResponseEntity.ok(service.store(courseDto));
    }

    @Operation(summary = "Lấy thông tin khóa học ",description = "Trả ra thông tin khóa học")
    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa khóa học ",description = "Xóa thông tin khóa học")
    public ResponseEntity<?> delete(@PathVariable String id) throws BadRequestException {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Lấy 1 khóa học ",description = "Lấy 1 thông tin khóa học")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id) throws BadRequestException {
        return ResponseEntity.ok(service.getOne(id));
    }

    @Operation(summary = "Cập nhật khóa học ",description = "Cập nhật 1 thông tin khóa học")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody @Valid UpdateCourseDto updateCourseDto) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(service.update(id,updateCourseDto));
    }
}
