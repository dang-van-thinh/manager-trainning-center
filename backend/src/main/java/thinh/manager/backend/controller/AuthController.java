package thinh.manager.backend.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thinh.manager.backend.service.AuthService;

@RestController
@Tag(name = "Auth",description = "Quản lý phần xác thực ")
@RequestMapping("api/auth")
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/test")
    public ResponseEntity<?> testAuth(@RequestParam("email") String email ,@RequestParam("password") String password ){
        return ResponseEntity.ok(authService.testAuth(email,password));
    }
}
