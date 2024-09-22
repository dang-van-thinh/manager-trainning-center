package thinh.manager.backend.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thinh.manager.backend.model.dto.auth.AuthRequest;
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

    @PostMapping("/login")
    public ResponseEntity<?> testAuth(@RequestBody AuthRequest request){
        return ResponseEntity.ok(authService.authentication(request));
    }
}
