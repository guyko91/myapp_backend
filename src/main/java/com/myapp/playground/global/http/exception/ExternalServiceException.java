package com.myapp.playground.global.http.exception;


import com.myapp.playground.global.http.ErrorCode;

public class ExternalServiceException extends BusinessException {

    private static final ErrorCode ERROR_CODE = ErrorCode.INTERNAL_SERVER_ERROR_EXTERNAL;

    public ExternalServiceException() {
        super(ERROR_CODE);
    }

    public ExternalServiceException(String message) {
        super(ERROR_CODE, message);
    }
}
