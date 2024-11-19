package com.myapp.playground.global.http.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myapp.playground.global.http.ResponseCode;
import com.myapp.playground.global.http.SuccessCode;import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public record ApiResult<T> (

    String code,
    String desc,

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    String message,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data

) {
    public static <T> ApiResult<T> of(ResponseCode responseCode, String message, T data) {
        return ApiResult.<T>builder()
            .code(responseCode.getCode())
            .desc(responseCode.getDesc())
            .message(message)
            .data(data)
            .build();
    }

    // 기본적인 OK 응답
    public static <T> ApiResult<T> ok() {
        return of(SuccessCode.OK, null, null);
    }

    // OK 응답 + 데이터
    public static <T> ApiResult<T> ok(T data) {
        return of(SuccessCode.OK, null, data);
    }

    // CREATED 응답
    public static <T> ApiResult<T> created() {
        return of(SuccessCode.CREATED, null, null);
    }

    // CREATED 응답 + 데이터
    public static <T> ApiResult<T> response(ResponseCode responseCode) {
        return of(responseCode, null, null);
    }

    // 커스텀 메시지와 데이터 응답
    public static <T> ApiResult<T> response(ResponseCode responseCode, String message) {
        return of(responseCode, message, null);
    }

    // 데이터만 있는 응답
    public static <T> ApiResult<T> response(ResponseCode responseCode, T data) {
        return of(responseCode, null, data);
    }

    // 메시지와 데이터가 있는 응답
    public static <T> ApiResult<T> response(ResponseCode responseCode, String message, T data) {
        return of(responseCode, message, data);
    }
}
