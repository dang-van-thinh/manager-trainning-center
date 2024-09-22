package thinh.manager.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thinh.manager.backend.entity.Mananger;
import thinh.manager.backend.model.dto.auth.AuthRequest;
import thinh.manager.backend.repository.ManagerRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class AuthService {
    private final ManagerRepository managerRepository;
    @Autowired
    public AuthService (
            ManagerRepository managerRepository
    ){
       this.managerRepository = managerRepository;
    }

    public Boolean authentication(AuthRequest request){
        Optional<Mananger> mananger = managerRepository.findByEmailAndAndPassword(request.getEmail(), request.getPassword());
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
