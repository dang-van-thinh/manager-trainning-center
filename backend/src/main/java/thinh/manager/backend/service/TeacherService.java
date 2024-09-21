package thinh.manager.backend.service;

import thinh.manager.backend.entity.Teacher;
import thinh.manager.backend.model.dto.teacher.TeacherDto;
import thinh.manager.backend.model.dto.teacher.UpdateTeacherDto;
import thinh.manager.backend.repository.TeacherRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository repository;

    @Autowired
    public TeacherService(
            TeacherRepository teacherRepository
    ) {
        this.repository = teacherRepository;
    }

    public List<TeacherDto> getAll() {
        return repository.findAll().stream().map(TeacherDto::toTeacherDto).toList();
    }
    
    public TeacherDto getTeacherDto(String id) {
        return TeacherDto.toTeacherDto(repository.findById(id)
                .orElseThrow(() -> new NullPointerException("Không tìm thấy giáo viên phù hợp !")));
    }

    public TeacherDto update(String id, UpdateTeacherDto request) throws BadRequestException {
        Teacher teacher = this.getTeacher(id);

        teacher.setQualifications(request.getQualifications());
        teacher.setSpecialize(request.getSpecialize());
        teacher.setPhone(request.getPhone());
        teacher.setEmail(request.getEmail());
        teacher.setGender(request.getGender());
        teacher.setFullName(request.getFullName());
        teacher.setHourlyRate(request.getHourlyRate());
        teacher.setBirthDay(request.getBirthDay());
        return TeacherDto.toTeacherDto(repository.save(teacher));
    }

    public Teacher getTeacher(String id){
        Optional<Teacher> teacher = repository.findById(id);

        if (teacher.isEmpty()){
            throw new NullPointerException("Không tìm thấy thông tin teacher phù hợp !");
        }
        return teacher.get();
    }
    
    public void delete(String id) throws BadRequestException {
        repository.delete(this.getTeacher(id));
    }

    public TeacherDto store(TeacherDto request) {
        Teacher teacher = Teacher.builder()
                .birthDay(request.getBirthDay())
                .phone(request.getPhone())
                .email(request.getEmail())
                .gender(request.getGender())
                .specialize(request.getSpecialize())
                .fullName(request.getFullName())
                .qualifications(request.getQualifications())
                .hourlyRate(request.getHourlyRate())
                .build();

        return TeacherDto.toTeacherDto(repository.save(teacher));
    }

    public List<TeacherDto> searchTeacher(String fullName, String gender , LocalDate startDate,LocalDate endDate,String specialize,String classId){
        return repository.searchTeacher(fullName,gender,startDate,endDate,specialize,classId).stream().map(TeacherDto::toTeacherDto).toList();
    }

//    public List<TeacherDto> search(String key){
//        String key1 = "%"+key+"%";
//        return repository.findAllByFullNameLikeOrGenderIsOrEmailLike(key1,key,key1).stream().map(EntityToDto::toTeacherDto).toList();
//    }


}
