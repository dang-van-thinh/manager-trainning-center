package thinh.manager.backend.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import thinh.manager.backend.model.response.errors.ApiErrorResponse;

import java.io.IOException;

@Component
public class CustomJwtAuthenEntrypoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
//        response.sendError(HttpStatus.UNAUTHORIZED.value(),"Unauthorized: Token is missing or invalid");

        // tham khảo
        // https://stackoverflow.com/questions/78361586/how-to-customise-error-handling-in-jwt-authentication-with-spring-security-6
        ApiErrorResponse error = new ApiErrorResponse();// Custom model class for you exception
        error.setCode(HttpStatus.FORBIDDEN.value());
        error.setMessage("Không có quyền truy cập !");

        response.setStatus(HttpStatus.FORBIDDEN.value());
        ServletOutputStream out = response.getOutputStream();
        new ObjectMapper().writeValue(out, error);
        out.flush();
    }
}
