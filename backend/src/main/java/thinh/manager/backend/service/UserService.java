package thinh.manager.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import thinh.manager.backend.entity.User;
import thinh.manager.backend.entity.enums.ERole;
import thinh.manager.backend.model.dto.user.UserDto;
import thinh.manager.backend.model.dto.user.UpdateUserDto;
import thinh.manager.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(
            UserRepository repository,
            PasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }
    public UserDto create (UserDto request){
       try {
           User user = User.builder()
                   .phone(request.getPhone())
                   .fullName(request.getFullName())
                   .email(request.getEmail())
                   .userName(request.getUsername())
                   .password(passwordEncoder.encode(request.getPassword()))
                   .gender(request.getGender())
                   .position(request.getPosition())
                   .birthDay(request.getBirthDay())
                   .position(request.getPosition())
                   .role(ERole.ADMIN)
                   .build();
           return UserDto.toUserDto(repository.save(user));
       }catch (NullPointerException exception){
           throw new NullPointerException(exception.getMessage());
       }
    }
    public List<UserDto> getAll(){
        return repository.findAll().stream().map(UserDto::toUserDto).toList();
    }

    public UserDto getManagerDto(String id){
        Optional<User>  mananger = repository.findById(id);
        if (mananger.isEmpty()){
            throw new RuntimeException("Không thấy thông tin người quản lý !");
        }
        return UserDto.toUserDto(mananger.get());
    }

    public User getManager(String id){
        Optional<User>  mananger = repository.findById(id);
        if (mananger.isEmpty()){
            throw new RuntimeException("Không thấy thông tin người quản lý !");
        }
       return mananger.get();
    }

    public UserDto update(String id, UpdateUserDto request){
        User user = this.getManager(id);

        user.setPosition(request.getPosition());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setGender(request.getGender());
        user.setUserName(request.getUsername());
        user.setBirthDay(request.getBirthDay());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        return UserDto.toUserDto(repository.save(user));
    }

    public void delete (String id){
        repository.delete(this.getManager(id));
    }
}
