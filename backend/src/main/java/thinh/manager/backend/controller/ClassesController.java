package thinh.manager.backend.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thinh.manager.backend.model.dto.classes.ClassesDto;
import thinh.manager.backend.model.dto.classes.ClassesResponse;
import thinh.manager.backend.model.dto.classes.UpdateClassesDto;
import thinh.manager.backend.service.ClassesService;

import java.util.List;

@RestController
@RequestMapping("api/classes")
@Tag(name = "Classes Management", description = "Quản lý lớp học")
public class ClassesController {

    @Autowired
    private ClassesService service;

    @Operation(summary = "Lấy tất cả lớp học ",description = "Trả ra danh sách lớp học ")
    @GetMapping
    public ResponseEntity<List<ClassesResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Thêm mới lớp học ",description = "Trả ra thông tin lớp học ")
    @PostMapping
    public ResponseEntity<?> store(@RequestBody @Valid ClassesDto request) throws BadRequestException {
        return ResponseEntity.ok(service.store(request));
    }


    @Operation(summary = "Cập nhật thông tin lớp học",description = "Cập nhật thông tin 1 lớp học")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateClassesDto request,
                                    @PathVariable String id
    ) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateClass(id, request));
    }

    @Operation(summary = "Xóa thông tin 1 lớp học",description = "Xóa thông tin 1 lớp học")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws BadRequestException {
        service.deleteClass(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Lấy chi tiết thông tin 1 lớp",description = "Lấy thông tin chi tiết 1 lớp học")
    @GetMapping("/{classId}")
    public ResponseEntity<?> getOne(@PathVariable String classId) throws BadRequestException {
        return ResponseEntity.ok(service.getOneClass(classId));
    }




}
