package thinh.manager.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thinh.manager.backend.entity.User;
import thinh.manager.backend.model.dto.auth.AuthRequest;
import thinh.manager.backend.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    @Autowired
    public AuthService (
            UserRepository userRepository
    ){
       this.userRepository = userRepository;
    }

    public Boolean authentication(AuthRequest request){
        Optional<User> mananger = userRepository.findByEmail(request.getEmail());
        if (mananger.isPresent()){
            return true;
        }
        throw new NullPointerException("Email hoặc mật khẩu không đúng !");
    }

    public Map<String, String> testAuth (String email , String password){
        Map<String , String> response = new HashMap<>();
        response.put("email", email);
        response.put("password",password);
        response.put("message","thành công rồi nhé !");
        log.info("Trả ra giá trị thành công");
        return response;
    }

}
