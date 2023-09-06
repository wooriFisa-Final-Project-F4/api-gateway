package f4.apigateway.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.Order;
import org.springframework.core.codec.Hints;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

@Slf4j
@Order(-1)
@RequiredArgsConstructor
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

  private final ObjectMapper objectMapper;

  @Override
  public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
    exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

    ErrorDetails errorDetails = null;

    if (ex instanceof ResponseStatusException) {
      errorDetails = new ErrorDetails(500, "Server Error");
    } else if (ex instanceof CustomException) {
      exchange.getResponse().setStatusCode(HttpStatus.valueOf(((CustomException) ex).getCustomErrorCode().getCode()));
      errorDetails = new ErrorDetails(
          ((CustomException) ex).getCustomErrorCode().getCode(),
          ((CustomException) ex).getCustomErrorCode().getMessage());
    } else {
      errorDetails = ErrorDetails.builder().code(404).message(ex.getMessage()).build();
    }

    return exchange.getResponse().writeWith(
        new Jackson2JsonEncoder(objectMapper).encode(
            Mono.just(errorDetails),
            exchange.getResponse().bufferFactory(),
            ResolvableType.forInstance(errorDetails),
            MediaType.APPLICATION_JSON,
            exchange.getAttribute(Hints.LOG_PREFIX_HINT)
        )
    );
  }
}