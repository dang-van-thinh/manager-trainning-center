package thinh.manager.backend.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import thinh.manager.backend.config.security.UserDetailService;
import thinh.manager.backend.model.response.errors.ApiErrorResponse;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtProvider service;

    @Autowired
    @Lazy
    private UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        log.info("doFilter Internal is run ! 1");
        try {
            // kiem tra token bat dau bang "Bearer "
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                username = service.extractUsername(token);
            }

            // If the token is valid and no authentication is set in the context
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                log.info("doFilter Internal is run !2");
                UserDetails userDetails = userDetailService.loadUserByUsername(username);

                // Validate token and set authentication
                log.info("Boolean validate token " + service.validateToken(token, userDetails));
                if (service.validateToken(token, userDetails)) {
                    log.info("doFilter Internal is run !3");
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception exception) {
            ObjectMapper mapper = new ObjectMapper(); // dung de map tu class sang json
            ApiErrorResponse errorResponse = new ApiErrorResponse();
            // Bắt lỗi khi JWT hết hạn
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            errorResponse.setCode(HttpStatus.UNAUTHORIZED.value());
            errorResponse.setMessage("Token đã hết hạn !");
            response.getWriter().write(mapper.writeValueAsString(errorResponse));
            return; // dung lai luon
        }
        filterChain.doFilter(request, response);
    }
}
