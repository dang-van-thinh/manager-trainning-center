package thinh.manager.backend.controller;

import thinh.manager.backend.model.dto.classroom.ClassRoomDto;
import thinh.manager.backend.model.dto.classroom.UpdateClassRoomDto;
import thinh.manager.backend.service.ClassRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/class-room")
@Tag(name = "Class Rom Management" , description = "Quản lý phòng học")
public class ClassRoomController {
    @Autowired
    private ClassRoomService service;

    @Operation(summary = "Thêm mới 1 lớp học ",description = "Thêm mới thông tin 1 lớp học ")
    @PostMapping
    public ResponseEntity<?> store(@RequestBody @Valid ClassRoomDto request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.store(request));
    }

    @Operation(summary = "Lấy danh sách phòng học",description = "Trả ra danh sách phòng học")
    @GetMapping
    public ResponseEntity<List<ClassRoomDto>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Lấy thông tin 1 phòng học",description = "Trả ra thông tin phòng học")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id){
        return ResponseEntity.ok(service.getRoomDto(id));
    }

    @Operation(summary = "Cập nhật thông tin phòng học",description = "Cập nhật thông tin 1 phòng học")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,
                                    @RequestBody UpdateClassRoomDto request){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateClassRoom(id,request));
    }

    @Operation(summary = "Xóa thông tin phòng học",description = "Xóa thông tin 1 phòng học")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        service.deleteClassRoom(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Xóa thành công phòng học !");
    }
}
