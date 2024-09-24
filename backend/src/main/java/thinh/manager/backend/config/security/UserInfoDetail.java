package thinh.manager.backend.config.security;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import thinh.manager.backend.entity.enums.ERole;
import thinh.manager.backend.model.dto.user.UserDto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


// lớp chứa thông tin người dùng dùng để xác thực
@Slf4j
@Getter
@Setter
public class UserInfoDetail implements UserDetails {

    private String username; // dang check bang email
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoDetail(UserDto userDto) {
        log.info("UserInfoDetail iss run !");
        this.password = userDto.getPassword();
        this.username = userDto.getEmail(); // ne chu y ko oang
        this.authorities = List.of(userDto.getRole()).stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.toString()))
                .collect(Collectors.toList());
        log.info("UserInfoDetail iss run ! +" + authorities);

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
