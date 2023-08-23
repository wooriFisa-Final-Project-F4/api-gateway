package f4.apigateway.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorDetails {

  private int code;
  private String message;
}
