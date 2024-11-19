package com.myapp.playground.infrastructure.google.exception;

import com.myapp.playground.global.http.ErrorCode;
import com.myapp.playground.global.http.exception.BusinessException;

public class GoogleCalendarException extends BusinessException {

    public GoogleCalendarException(String message) {
        super(ErrorCode.INTERNAL_SERVER_ERROR_EXTERNAL, message);
    }

    public GoogleCalendarException() {
        super(ErrorCode.INTERNAL_SERVER_ERROR_EXTERNAL);
    }

}
