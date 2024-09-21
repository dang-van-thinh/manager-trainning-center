package thinh.manager.backend.service;

import thinh.manager.backend.entity.*;
import thinh.manager.backend.model.dto.schedule.ScheduleDto;
import thinh.manager.backend.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ScheduleService {
    private final ScheduleRepository repository;

    @Autowired
    public ScheduleService(
            ScheduleRepository repository
    ) {
        this.repository = repository;
    }

    public ScheduleDto create(ScheduleDto request){
        Schedule schedule = Schedule.builder()
                .classes(request.getClassesID())
                .build();
       return ScheduleDto.toScheduleDto(repository.save(schedule));
    }

    public List<ScheduleDto> getAll() {
        return repository.findAll()
                .stream().map(ScheduleDto::toScheduleDto).toList();
    }

    //     check trung lich theo ngay va ca
    public boolean isDuplicateSchedule(String teacherID, Integer session, Integer day) {
        List<Schedule> scheduleList = repository.findAllByTeacher(teacherID, day, session);
        boolean check = false;
        if (!scheduleList.isEmpty()) check = true;
        return check;
    }
    public Schedule getSchedule(String scheduleId) throws BadRequestException {
        return repository.findById(scheduleId).orElseThrow(() -> new BadRequestException("Không tìm thấy lịch trình phù hợp phù hợp "));
    }

    // dem ca hoc cua giao vien
    public Integer countSessionByTeacher(String teacherId){
        return repository.countSessionByTeacher(teacherId);
    }

    public void delete(String scheduleId) throws BadRequestException {
        repository.delete(this.getSchedule(scheduleId));
    }
}
