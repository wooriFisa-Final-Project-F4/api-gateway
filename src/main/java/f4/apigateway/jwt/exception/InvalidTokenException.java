package f4.apigateway.jwt.exception;


import f4.apigateway.exception.CustomErrorCode;
import f4.apigateway.exception.CustomException;
import lombok.Getter;

@Getter
public class InvalidTokenException extends CustomException {

  public InvalidTokenException(CustomErrorCode customErrorCode) {
    super(customErrorCode);
  }
}