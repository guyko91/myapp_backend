package com.myapp.playground.infrastructure.google.exception;

import com.myapp.playground.global.http.ErrorCode;
import com.myapp.playground.global.http.exception.BusinessException;

public class GoogleOAuthException extends BusinessException {

    public GoogleOAuthException(String message) {
        super(ErrorCode.INTERNAL_SERVER_ERROR_EXTERNAL, message);
    }

    public GoogleOAuthException() {
        super(ErrorCode.INTERNAL_SERVER_ERROR_EXTERNAL);
    }

}
