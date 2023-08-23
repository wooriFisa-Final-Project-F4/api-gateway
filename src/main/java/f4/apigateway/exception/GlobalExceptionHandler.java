package f4.apigateway.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({CustomException.class})
  public ResponseEntity<?> customExceptionHandler(CustomException e) {
    log.error(
        "errorCode: {}, message: {}",
        e.getCustomErrorCode().getCode(),
        e.getCustomErrorCode().getMessage());

    return new ResponseEntity<>(
        ErrorDetails.builder()
            .code(e.getCustomErrorCode().getCode())
            .message(e.getCustomErrorCode().getMessage())
            .build(),
        HttpStatus.BAD_REQUEST);
  }
}
