package thinh.manager.backend.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import thinh.manager.backend.entity.User;
import thinh.manager.backend.model.dto.user.UserDto;
import thinh.manager.backend.repository.UserRepository;

import java.util.Optional;

@Service
@Slf4j
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(email);
        log.info("loadUserByUsername is run !");
        // convert sang cái lớp triển khai của interface userDetails
//        c1:
//        UserDto userDto = UserDto.toUserDto(user.get());
//        UserInfoDetail userInfoDetail = new UserInfoDetail(userDto);
        // c2:
        UserInfoDetail users = user.map(UserDto::toUserDto).map(UserInfoDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException("Email hoặc mật khẩu không chính xác !"));
        log.info("User name after load : " + users);
        return users;
    }


}
