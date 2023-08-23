package f4.apigateway.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum OpenApiEndpoints {
  REGISTER("/auth/v1/register", "회원가입 API"),
  LOGIN("/auth/v1/login", "로그인 API"),
  EUREKA("/eureka", "Eureka 서비스"),
  EMAIL_SEND("/email/v1/send", "이메일 인증 코드 발송 API");

  private final String path;
  private final String description;

  public static List<String> allPaths() {
    return Arrays.stream(values()).map(OpenApiEndpoints::getPath).collect(Collectors.toList());
  }
}
