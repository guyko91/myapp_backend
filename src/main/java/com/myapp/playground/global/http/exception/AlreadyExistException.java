package com.myapp.playground.global.http.exception;


import com.myapp.playground.global.http.ErrorCode;

public class AlreadyExistException extends BusinessException {

    private static final ErrorCode ERROR_CODE = ErrorCode.CONFLICT;

    public AlreadyExistException() {
        super(ERROR_CODE);
    }

    public AlreadyExistException(String message) {
        super(ERROR_CODE, message);
    }
}
