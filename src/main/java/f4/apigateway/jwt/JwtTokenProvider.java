package f4.apigateway.jwt;

import f4.apigateway.exception.CustomErrorCode;
import f4.apigateway.exception.CustomException;
import f4.apigateway.jwt.exception.ExpiredTokenException;
import f4.apigateway.jwt.exception.InvalidTokenException;
import f4.apigateway.redis.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  private final RedisService redisService;
  @Value("${jwt.secret}")
  private String SECRET_KEY;

  @Value("${jwt.token.access-token-duration}")
  private Long atkDuration;

  @Value("${jwt.token.refresh-token-duration}")
  private Long rtkDuration;

  @Value("${jwt.token.prefix}")
  private String prefix;

  private Key getSigningKey(String secretKey) {
    byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public Claims extractAllClaims(String token) {
    try {
      return Jwts.parserBuilder()
          .setSigningKey(getSigningKey(SECRET_KEY))
          .build()
          .parseClaimsJws(token)
          .getBody();
    } catch (ExpiredJwtException e) {
      throw new CustomException(CustomErrorCode.EXPIRED_ACCESS_TOKEN);
    } catch (InvalidTokenException e) {
      throw new CustomException(CustomErrorCode.INVALID_ACCESS_TOKEN);
    }
  }

  public String resolveToken(ServerHttpRequest request) {
    String bearer = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
    return parseToken(bearer);
  }

  public String parseToken(String bearerToken) {
    if (bearerToken != null && bearerToken.startsWith(prefix)) {
      return bearerToken.replace(prefix, "");
    }
    throw new CustomException(CustomErrorCode.MISSING_AUTHORIZATION_HEADER);
  }
}