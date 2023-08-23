package f4.apigateway.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
    MISSING_AUTHORIZATION_HEADER(402, "요청에 Authorization 헤더가 없습니다."),
    UN_AUTHORIZED_ACCESS_TO_APPLICATION(401, "인증받은 토큰이 아닙니다.");
    private final int code;
    private final String message;
}
