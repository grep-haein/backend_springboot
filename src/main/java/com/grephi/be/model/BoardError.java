package com.grephi.be.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum BoardError {

    ACCOUNT_ALREADY_EXISTS(HttpStatus.CONFLICT, "AccountAlreadyExists", "사용중인 계정명 입니다."),

    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "ExpiredToken", "토큰이 만료되었습니다."), // 400

    INTERNAL_ERROR(HttpStatus.BAD_REQUEST, "InternalError", "내부 오류가 발생하였습니다. 다시 시도해 주세요."), // 400

    INVALID_ACCOUNT(HttpStatus.BAD_REQUEST, "NoSuchAccount", "계정정보를 확인해 주세요."), // 400

    UNEXPECTED_CONTENT(HttpStatus.BAD_REQUEST, "UnexpectedContent", "유효하지 않은 요청입니다."), // 400

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthenticated", "인증되지 않은 상태입니다."), // 401 비인증

    ACCESS_DENIED(HttpStatus.FORBIDDEN, "AccessDenied", "접근이 거부되었습니다."), // 403 컨텐츠 접근 권한 없음

    NOTFOUND_RESOURCE(HttpStatus.NOT_FOUND, "Not Found Resource", "요청받은 리소스를 찾을 수 없습니다."), // 404 페이지 찾을 수 없음

    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "Method Not Allowed", "요청한 메소드를 사용할 수 없습니다."), // 405 요청한 메소드를 사용할 수 없음

    REQUEST_TIMEOUT(HttpStatus.REQUEST_TIMEOUT, "Request Timeout", "요청 시간이 초과되었습니다."), // 408 요청 후 응답 시간이 오래 경과함

    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type", "지원하지 않는 미디어 포맷입니다."), // 415 요청한 미디어 포맷을 지원하지 않음,

    FAILED_DEPENDENCY(HttpStatus.FAILED_DEPENDENCY, "Failed Dependency","이전 요청이 실패하였기 때문에 지금의 요청도 실패하였습니다."), // 424 이전 요청 실패 -> 지금 요청 실패

    HIBERNATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "HibernateError", "내부 서버 오류입니다."), // 500 내부 서버 오류

    BAD_GATEWAY(HttpStatus.BAD_GATEWAY, "Bad Gateway", "잘못된 응답입니다."), // 502 게이트웨이로부터 잘못된 응답을 수신함

    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "", "요청한 정보를 처리할 수 없습니다"), // 503 요청을 처리할 준비가 안됨

    HTTP_VERSION_NOT_SUPPORTED(HttpStatus.HTTP_VERSION_NOT_SUPPORTED,"HTTP Version Not Supported","서버에서 지원하지 않는 HTTP 버전입니다."), // 505 서버에서 지원하지 않는 HTTP 버전을 클라이언트가 요청함

    NETWORK_AUTHENTICATION_REQUIRED(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED,"Network Authentication Required","네트워크 엑세스를 얻기 위해 인증이 필요합니다."); // 511 클라이언트가 네트워크 엑세스를 얻기 위해 인증할 필요가 있음


    private final HttpStatus httpStatus;

    private final String code;

    private final String description;

//    private BoardError(HttpStatus httpStatus, String code, String description) {
//        this.httpStatus = httpStatus;
//        this.code = code;
//        this.description = description;
//    }

//    public HttpStatus getHttpStatus() {
//        return httpStatus;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public String getDescription() {
//        return description;
//    }

}
