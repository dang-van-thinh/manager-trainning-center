package thinh.manager.backend.service;

import thinh.manager.backend.entity.Mananger;
import thinh.manager.backend.model.dto.manager.ManagerDto;
import thinh.manager.backend.model.dto.manager.UpdateManagerDto;
import thinh.manager.backend.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {
    private ManagerRepository repository;

    @Autowired
    public ManagerService(
            ManagerRepository repository
    ) {
        this.repository = repository;
    }
    public ManagerDto create (ManagerDto request){
       try {
           Mananger mananger = Mananger.builder()
                   .phone(request.getPhone())
                   .fullName(request.getFullName())
                   .email(request.getEmail())
                   .userName(request.getUsername())
                   .password(request.getPassword())
                   .gender(request.getGender())
                   .position(request.getPosition())
                   .birthDay(request.getBirthDay())
                   .position(request.getPosition())
                   .build();
           return ManagerDto.toMangerDto(repository.save(mananger));
       }catch (NullPointerException exception){
           throw new NullPointerException(exception.getMessage());
       }
    }
    public List<ManagerDto> getAll(){
        return repository.findAll().stream().map(ManagerDto::toMangerDto).toList();
    }

    public ManagerDto getManagerDto(String id){
        Optional<Mananger>  mananger = repository.findById(id);
        if (mananger.isEmpty()){
            throw new RuntimeException("Không thấy thông tin người quản lý !");
        }
        return ManagerDto.toMangerDto(mananger.get());
    }

    public Mananger getManager(String id){
        Optional<Mananger>  mananger = repository.findById(id);
        if (mananger.isEmpty()){
            throw new RuntimeException("Không thấy thông tin người quản lý !");
        }
       return mananger.get();
    }

    public ManagerDto update(String id, UpdateManagerDto request){
        Mananger mananger = this.getManager(id);

        mananger.setPosition(request.getPosition());
        mananger.setPassword(request.getPassword());
        mananger.setPhone(request.getPhone());
        mananger.setGender(request.getGender());
        mananger.setUserName(request.getUsername());
        mananger.setBirthDay(request.getBirthDay());
        mananger.setEmail(request.getEmail());
        mananger.setFullName(request.getFullName());
        return ManagerDto.toMangerDto(repository.save(mananger));
    }

    public void delete (String id){
        repository.delete(this.getManager(id));
    }
}
