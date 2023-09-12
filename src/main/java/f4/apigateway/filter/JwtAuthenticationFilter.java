package f4.apigateway.filter;


import f4.apigateway.exception.CustomErrorCode;
import f4.apigateway.exception.CustomException;
import f4.apigateway.jwt.JwtTokenProvider;
import f4.apigateway.redis.RedisService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

  private final JwtTokenProvider jwtTokenProvider;
  private final RouteValidator routeValidator;
  private final RedisService redisService;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();

    if (routeValidator.isSecured.test(request)) {
      log.info("요청중");
      String accessToken = jwtTokenProvider.resolveToken(request);

      // 레디스 블랙리스트에 해당 토큰이 있는지 여부  파악
      if(redisService.hasBlackList(accessToken)){
        throw new CustomException(CustomErrorCode.INVALID_ACCESS_TOKEN);
      }

      Claims claims = jwtTokenProvider.extractAllClaims(accessToken);
      addAuthorizationHeaders(request, claims);
    }

    HttpHeaders headers = request.getHeaders();
    log.info("해당 서비스를 실행합니다. path : {}", request.getURI());
    return chain.filter(exchange);
  }

  private void addAuthorizationHeaders(ServerHttpRequest request, Claims claims) {
    request.mutate()
        .header("userId", String.valueOf(claims.get("userId")))
        .header("role", (String) claims.get("role"))
        .build();
  }

  @Override
  public int getOrder() {
    return -1; // filter 실행 순서, 낮은 숫자가 먼저 실행됩니다.
  }
}