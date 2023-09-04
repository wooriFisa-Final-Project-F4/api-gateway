package f4.apigateway.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
    // Bad Request 400
    ALREADY_LOGOUT_USER(400, "이미 로그아웃된 유저입니다."),

    // Unathorized 401
    EXPIRED_ACCESS_TOKEN( 401, "만료된 엑세스토큰 입니다"),
    EXPIRED_REFRESH_TOKEN( 401, "만료된 리프레시토큰 입니다"),
    INVALID_ACCESS_TOKEN(401, "잘못된 토큰 입니다"),
    UN_SUPPORTED_TOKEN(401, "지원하지 않는 토큰입니다"),

    // Forbidden 402,
    MISSING_AUTHORIZATION_HEADER(402, "요청에 Authorization 헤더가 없습니다."),
    MISSING_REFRESH_TOKEN(402, "쿠키에 refresh-token이 없습니다."),

    // Not Found 404

    ;
    private final int code;
    private final String message;
}
