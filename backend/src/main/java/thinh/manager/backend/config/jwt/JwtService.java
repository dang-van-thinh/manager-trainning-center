package thinh.manager.backend.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtService {

    private final String SecretKey = "ZjhjZDg0NGU4MGExYmE1MDdkZjQyMDM4OTBjMTY5MmNiNzM0YWUzM2M4YTE1NjkyNzA4YjljNTgxMzFkMDNjYw";

    public String generateToken(String email) {
        log.info("Chạy vào generateToken ");
        return Jwts.builder()
                .setSubject(email)
                .claim("role","ADMIN")
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10)) // set time 10phut
                .signWith(SignatureAlgorithm.HS256,SecretKey)
                .compact();
    }

    public Claims decodeToken(String token) {
        log.info("Đang chạy vào decode");
        log.info(token);
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SecretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception exception){
            throw new RuntimeException("JWT "+exception.getMessage());
        }
    }

    public String extractUsername(String token){
        log.info("Email set : "+ decodeToken(token).getSubject() );
        return decodeToken(token).getSubject();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        log.info("isTokenExpired is run !");
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        log.info("extractExpiration is run !");

        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = decodeToken(token);
        return claimsResolver.apply(claims);
    }



}
