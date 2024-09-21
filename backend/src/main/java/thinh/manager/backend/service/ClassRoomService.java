package thinh.manager.backend.service;

import thinh.manager.backend.entity.ClassRoom;
import thinh.manager.backend.model.dto.classroom.ClassRoomDto;
import thinh.manager.backend.model.dto.classroom.UpdateClassRoomDto;
import thinh.manager.backend.repository.ClassRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassRoomService {
    @Autowired
    private ClassRoomRepository repository;
    
    public ClassRoomDto store(ClassRoomDto request) {
        ClassRoom classRoom = ClassRoom.builder()
                .capacity(request.getCapacity())
                .name(request.getName())
                .location(request.getLocation())
                .build();
        return ClassRoomDto.toClassRoomDto(repository.save(classRoom));
    }

    public List<ClassRoomDto> getAll() {
        return repository.findAll().stream().map(ClassRoomDto::toClassRoomDto).toList();
    }

    public ClassRoomDto getRoomDto(String id) {
        return ClassRoomDto.toClassRoomDto(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng học phù hợp !")));
    }

    public ClassRoom getRoom(String id) throws RuntimeException {

        Optional<ClassRoom> classRoom = repository.findById(id);

        if (classRoom.isEmpty()){
            throw new RuntimeException("Không tìm thấy phòng học phù hợp !");
        }

        return classRoom.get();
    }

    public ClassRoomDto updateClassRoom(String id, UpdateClassRoomDto classRoomDto) {
        ClassRoom classRoom = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin lớp !"));

        classRoom.setLocation(classRoomDto.getLocation());
        classRoom.setName(classRoomDto.getName());
        classRoom.setCapacity(classRoomDto.getCapacity());

        return ClassRoomDto.toClassRoomDto(repository.save(classRoom));
    }

    public void deleteClassRoom(String id) {
        repository.deleteById(id);
    }
}
