package thinh.manager.backend.controller;

import thinh.manager.backend.service.IncomeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/incomes")
@Tag(name = "Income Manager", description = "Quản lý thông tin khoản thu ")
public class IncomeController {
    @Autowired
    private IncomeService service;

    @GetMapping
    public Double totalAmount(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate){
        return service.totalIncome(startDate,endDate );
    }
}
