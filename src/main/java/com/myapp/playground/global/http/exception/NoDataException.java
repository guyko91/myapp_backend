package com.myapp.playground.global.http.exception;


import com.myapp.playground.global.http.ErrorCode;

public class NoDataException extends BusinessException {

    private static final ErrorCode ERROR_CODE = ErrorCode.NO_DATA;

    public NoDataException() {
        super(ERROR_CODE);
    }

    public NoDataException(String message) {
        super(ERROR_CODE, message);
    }
}
