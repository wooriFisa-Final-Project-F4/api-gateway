package f4.apigateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import f4.apigateway.exception.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ErrorExceptionConfig {
  private final ObjectMapper objectMapper;
  @Bean
  public ErrorWebExceptionHandler globalExceptionHandler() {
    return new GlobalExceptionHandler(objectMapper);
  }
}
