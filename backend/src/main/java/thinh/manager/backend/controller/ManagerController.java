package thinh.manager.backend.controller;

import thinh.manager.backend.model.dto.manager.ManagerDto;
import thinh.manager.backend.model.dto.manager.UpdateManagerDto;
import thinh.manager.backend.service.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Manager Management",description = "Quản lý quản trị viên")
@RequestMapping("api/managers")
public class ManagerController {
    @Autowired
    private ManagerService service;

    @Operation(summary = "Lấy ra danh sách quản trị viên",description = "Trả ra danh sách quản trị viên")
    @GetMapping
    public ResponseEntity<List<ManagerDto>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Tạo mới 1 quản trị viên", description = "Trả ra thông tin quản trị viên vừa tạo")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ManagerDto managerDto){
        return ResponseEntity.ok(service.create(managerDto));
    }

    @Operation(summary = "Xóa thông tin quản trị viên", description = "Xóa thông tin 1 quản trị viên")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete( @PathVariable String id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Lấy thông tin quản trị viên", description = "Trả ra thông tin chi tiết của quản tri viên")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getManagerDto(id));
    }

    @Operation(summary = "Cập nhật thông tin quản trị viên", description = "Cập nhật thông tin 1 quản trị viên")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody @Valid UpdateManagerDto updateManagerDto){
        return ResponseEntity.ok(service.update(id,updateManagerDto));
    }

}
