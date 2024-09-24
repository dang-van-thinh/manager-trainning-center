package thinh.manager.backend.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import thinh.manager.backend.config.jwt.JwtService;
import thinh.manager.backend.model.dto.auth.AuthRequest;
import thinh.manager.backend.model.response.TokenResponse;
import thinh.manager.backend.service.AuthService;

@RestController
@Tag(name = "Auth",description = "Quản lý phần xác thực ")
@RequestMapping("api/auth")
public class AuthController {
    private AuthService authService;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(
            AuthService authService,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.authService = authService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> testAuth(@RequestBody AuthRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        if (authentication.isAuthenticated()){
            return ResponseEntity.ok(new TokenResponse(HttpStatus.OK.value(), jwtService.generateToken(request.getEmail())));
        }else {
            throw new UsernameNotFoundException("Request không hợp lệ !");
        }
    }
}
