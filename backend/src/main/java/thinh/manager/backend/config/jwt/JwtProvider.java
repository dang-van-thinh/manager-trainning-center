package thinh.manager.backend.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtProvider {
    @Value("${jwt.serect-key}")
    private String SecretKey;
    private final long ACCESS_EXPIRATION = 600000L; // access token - 10 phut
    private final long REFRESH_EXPIRATION = 600000000L; // refresh token

    public String generateAccessToken(String email) {
        log.info("Chạy vào generateToken ");
        Date expired = new Date(new Date().getTime() + ACCESS_EXPIRATION);
        return Jwts.builder()
                .setSubject(email)
//                .claim("role","ADMIN") // thu voi nhung thong tin khac
                .setIssuedAt(new Date())
                .setExpiration(expired) // set time 10phut
                .signWith(SignatureAlgorithm.HS256,SecretKey)
                .compact();
    }

    public String generateRefreshToken(String email){
        Date expired = new Date(new Date().getTime() + REFRESH_EXPIRATION);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
//                .claim("role","ADMIN") // thu voi nhung thong tin khac
                .setExpiration(expired) // set time 10phut
                .signWith(SignatureAlgorithm.HS256,SecretKey)
                .compact();
    }

    public Claims extractAllToken(String token) {
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

    public String extractEmail(String token){
        log.info("Email set : "+ extractAllToken(token).getSubject() );
        return extractAllToken(token).getSubject();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractEmail(token);
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
        final Claims claims = extractAllToken(token);
        return claimsResolver.apply(claims);
    }
}
