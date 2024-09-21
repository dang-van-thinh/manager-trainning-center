package thinh.manager.backend.controller;

import thinh.manager.backend.model.dto.schedule.ScheduleDto;
import thinh.manager.backend.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Schedule management" , description = "Quản lý thông tin lịch học")
@RequestMapping("api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService service;

//    @Operation(summary = "Thêm mới 1 lịch học",description = "Thêm mới thông tin 1 lịch học")
//    @PostMapping
//    public ResponseEntity<ScheduleDto> create(@RequestBody @Valid ScheduleDto scheduleDto, BindingResult result) throws BadRequestException {
//        return ResponseEntity.ok(service.create(scheduleDto));
//    }

    @Operation(summary = "Lấy danh sách lịch học",description = "Lấy thông tin lịch học chung")
    @GetMapping
    public ResponseEntity<List<ScheduleDto>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<?> delete(@PathVariable String scheduleId) throws BadRequestException {
         service.delete(scheduleId);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
