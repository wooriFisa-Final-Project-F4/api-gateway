package f4.apigateway.jwt.exception;

import f4.apigateway.exception.CustomErrorCode;
import f4.apigateway.exception.CustomException;
import lombok.Getter;

@Getter
public class ExpiredTokenException extends CustomException {

  public ExpiredTokenException(CustomErrorCode customErrorCode) {
    super(customErrorCode);
  }
}