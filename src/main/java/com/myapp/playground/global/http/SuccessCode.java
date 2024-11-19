package com.myapp.playground.global.http;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum SuccessCode implements  ResponseCode{

    OK(HttpStatus.OK,"20000000", "요청 성공"),
    CREATED(HttpStatus.CREATED, "20100000", "생성 성공"),
    ACCEPTED(HttpStatus.ACCEPTED, "20200000", "처리가 완료되지 않음"),
    NO_CONTENT(HttpStatus.NO_CONTENT, "20400000", "요청 성공(컨텐츠 없음)"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String desc;

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }

    @Override
    public String getCode() {
        return "";
    }

    @Override
    public String getDesc() {
        return "";
    }
}
