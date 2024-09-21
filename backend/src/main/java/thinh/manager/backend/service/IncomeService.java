package thinh.manager.backend.service;

import thinh.manager.backend.entity.Income;
import thinh.manager.backend.model.dto.income.IncomeDto;
import thinh.manager.backend.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class IncomeService {
    private final IncomeRepository repository;

    @Autowired
    public IncomeService(IncomeRepository repository) {
        this.repository = repository;
    }

    public IncomeDto create(IncomeDto incomeDto){
        Income income = Income.builder()
                .id(incomeDto.getId())
                .amount(incomeDto.getAmount())
                .date(incomeDto.getDate())
                .description(incomeDto.getDescription())
                .title(incomeDto.getTitle())
                .note(incomeDto.getNote())
                .paymentMethod(incomeDto.getPaymentMethod())
                .receivedFrom(incomeDto.getReceivedFrom())
                .coursesId(incomeDto.getCourseId())
                .studentId(incomeDto.getStudentId())
                .build();

        return IncomeDto.incomeDto(repository.save(income));
    }

    public Income getIncome(String studentId,String courseId){
        Optional<Income> income =  repository.findByStudentIdIsAndCoursesIdIs(studentId,courseId);
        if (income.isEmpty()){
            throw new NullPointerException("Không tìm thấy hóa đơn phù hợp !");
        }
        return income.get();
    }
    // test tinh tong tien thu tu hoc vien
    public Double totalIncome(LocalDate startTime,LocalDate endTime){
        return repository.toTalIncome(startTime,endTime);
    }

    public void delete(Income income){
        repository.delete(income);
    }
}
