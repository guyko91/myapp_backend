package com.myapp.playground.global.http;


import org.springframework.http.HttpStatus;

public interface ResponseCode {

    String name();
    HttpStatus getHttpStatus();
    String getCode();
    String getDesc();

}
