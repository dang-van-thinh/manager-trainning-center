package thinh.manager.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thinh.manager.backend.repository.StudentRepository;
import thinh.manager.backend.service.StudentService;

@RestController
@RequestMapping("/verify-email")
public class VerifyController {
    private StudentService studentService;

    @Autowired
    public VerifyController(
            StudentService studentService
    ){
        this.studentService =studentService;
    }

    @GetMapping("{id}")
    public String verifyEmail(@PathVariable String id){
        return "Xác thực thành công !";
    }
}
