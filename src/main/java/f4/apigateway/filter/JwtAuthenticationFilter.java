package f4.apigateway.filter;


import f4.apigateway.exception.CustomErrorCode;
import f4.apigateway.exception.CustomException;
import f4.apigateway.jwt.JwtTokenProvider;
import f4.apigateway.redis.RedisService;
import f4.apigateway.utils.CookieUtil;
import io.jsonwebtoken.Claims;
import io.netty.handler.codec.Headers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

  private final JwtTokenProvider jwtTokenProvider;
  private final RedisService redisService;
  private final CookieUtil cookieUtil;
  private final RouteValidator routeValidator;
  private final RestTemplate restTemplate;

  @Value("${jwt.token.prefix}")
  private String prefix;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();
    ServerHttpResponse response = exchange.getResponse();

    if (routeValidator.isSecured.test(request)) {
      log.info("요청중");
      Claims claims = null;
//      try {
        String accessToken = jwtTokenProvider.resolveToken(request);
        claims = jwtTokenProvider.extractAllClaims(accessToken);
        request = addAuthorizationHeaders(request, claims);
//      } catch (CustomException e) {
//        log.info("access-token이 만료되어 refresh-token을 활용하여 access-token을 새로 발급받는다.");
//        String refreshToken = cookieUtil.getCookie(request, "refresh-token");
//
//        if (!refreshToken.isBlank()) {
//          return chain.filter(exchange.mutate().request(request).build());
//        }

//        throw new CustomException(CustomErrorCode.MISSING_REFRESH_TOKEN);
    }

    HttpHeaders headers = request.getHeaders();
    log.info("해당 서비스를 실행합니다. path : {}", request.getURI());
    log.info("request-header userId : {}", headers.getFirst("userId"));
    return chain.filter(exchange.mutate().request(request).build());
  }

  private ServerHttpRequest addAuthorizationHeaders(ServerHttpRequest request, Claims claims) {
    return request.mutate()
        .header("userId", String.valueOf(claims.get("user_id")))
        .header("role", (String) claims.get("role"))
        .build();
  }

  @Override
  public int getOrder() {
    return -1; // filter 실행 순서, 낮은 숫자가 먼저 실행됩니다.
  }
}