package thinh.manager.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thinh.manager.backend.repository.ManagerRepository;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String, String> testAuth (String email , String password){
        Map<String , String> response = new HashMap<>();
        response.put("email", email);
        response.put("password",password);
        response.put("message","thành công rồi nhé !");
        log.info("Trả ra giá trị thành công");
        return response;
    }

}
