package com.myapp.playground.global.http.exception.handler;

import com.myapp.playground.global.http.ErrorCode;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(1)
@RestControllerAdvice
public class BusinessExceptionHandler extends GlobalExceptionHandler {

    @Override
    protected ResponseEntity<Object> createErrorResponseEntity(ErrorCode errorCode) {
        return super.createErrorResponseEntity(errorCode);
    }

    @Override
    protected ResponseEntity<Object> createErrorResponseEntity(ErrorCode errorCode, String message) {
        return super.createErrorResponseEntity(errorCode, message);
    }
}
