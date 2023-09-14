package f4.apigateway.constant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OpenApiEndpoints {
  REGISTER("/user/v1/signup", "회원가입 API"),
  LOGIN("/auth/v1/login", "로그인 API"),
  LOGOUT("/auth/v1/logout", "로그아웃 API"),
  EUREKA("/eureka", "Eureka 서비스"),
  EMAIL_SEND("/email/v1/code", "이메일 인증 코드 발송 API"),
  HEALTH("/system", "Actuator 정보"),
  EMAIL_CERTIFICATION("/auth/v1/email/certification", "이메일 인증 API"),
  REISSUE("/auth/v1/token/reissue", "토큰 재발행"),
  HEALTH_SCHEDULER("/scheduler/","스케줄러 테스트 컨트롤러"),
  PRODUCT_LIST("/product/v1/", "상품");


  private final String path;
  private final String description;

  public static List<String> allPaths() {
    return Arrays.stream(values()).map(OpenApiEndpoints::getPath).collect(Collectors.toList());
  }
}
