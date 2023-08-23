package f4.apigateway.filter;

import f4.apigateway.exception.CustomErrorCode;
import f4.apigateway.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
  private final RouteValidator validator;
  private final RestTemplate template;

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      if (shouldValidateRequest(exchange.getRequest())) {
        String token = extractTokenFromRequest(exchange.getRequest());
        validateToken(token);
      }
      return chain.filter(exchange);
    };
  }

  private boolean shouldValidateRequest(ServerHttpRequest request) {
    return validator.isSecured.test(request) && !request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
  }

  private String extractTokenFromRequest(ServerHttpRequest request) {
    String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      throw new CustomException(CustomErrorCode.MISSING_AUTHORIZATION_HEADER);
    }
    return authHeader.substring(7);
  }

  private void validateToken(String token) {
    try {
      template.getForObject("http://AUTH-SERVICE/validate?token=" + token, String.class);
    } catch (Exception e) {
      throw new CustomException(CustomErrorCode.UN_AUTHORIZED_ACCESS_TO_APPLICATION);
    }
  }

  public static class Config {}
}
