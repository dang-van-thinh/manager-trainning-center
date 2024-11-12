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
import thinh.manager.backend.config.jwt.JwtProvider;
import thinh.manager.backend.model.dto.auth.AuthRequest;
import thinh.manager.backend.model.response.TokenResponse;
import thinh.manager.backend.service.AuthService;

@RestController
@Tag(name = "Auth",description = "Quản lý phần xác thực ")
@RequestMapping("api/auth")
public class AuthController {
    private JwtProvider jwtProvider;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(
            JwtProvider jwtProvider,
            AuthenticationManager authenticationManager
    ) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> testAuth(@RequestBody AuthRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        if (authentication.isAuthenticated()){
            String token = jwtProvider.generateAccessToken(request.getEmail());
            return ResponseEntity.ok(new TokenResponse(token,jwtProvider.extractExpiration(token) ));
        }else {
            throw new UsernameNotFoundException("Request không hợp lệ !");
        }
    }
}
