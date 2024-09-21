package thinh.manager.backend.service;

import thinh.manager.backend.entity.PayRoll;
import thinh.manager.backend.entity.Teacher;
import thinh.manager.backend.model.dto.payroll.PayrollDto;
import thinh.manager.backend.model.dto.teacher.TeacherDto;
import thinh.manager.backend.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PayrollService {

    private final ScheduleService scheduleService;
    private final PayrollRepository repository;
    private final TeacherService teacherService;

    @Autowired
    public PayrollService(
            PayrollRepository repository,
            ScheduleService scheduleService,
            TeacherService teacherService
    ) {
        this.repository = repository;
        this.scheduleService = scheduleService;
        this.teacherService = teacherService;
    }


    // tao bang luong cho giao vien
    public List<PayrollDto> create() {
        List<TeacherDto> teacherList = teacherService.getAll();
        List<PayrollDto> payrollDtos = new ArrayList<>();

        for (TeacherDto teacher: teacherList
             ) {
            int totalSessionByTeacher = scheduleService.countSessionByTeacher(teacher.getId());
            int totalHours = totalSessionByTeacher * 2;
            double totalSalary = (teacher.getHourlyRate()) * totalHours;

            PayRoll payRoll = PayRoll.builder()
                    .teacher(teacher.getId())
                    .totalHours(totalHours)
                    .totalSalary(totalSalary)
                    .payDateStart(LocalDate.parse("2024-07-03"))
                    .payDateEnd(LocalDate.parse("2024-08-03"))
                    .build();
            payrollDtos.add(PayrollDto.payrollDto(repository.save(payRoll)));
        }
        return payrollDtos;
    }
}
