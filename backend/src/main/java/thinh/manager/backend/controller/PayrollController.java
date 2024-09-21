package thinh.manager.backend.controller;

import thinh.manager.backend.model.dto.payroll.PayrollDto;
import thinh.manager.backend.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/payrolls")
public class PayrollController {
    @Autowired
    private PayrollService service;

    @GetMapping
    public List<PayrollDto> create(){
        return service.create();
    }
}
