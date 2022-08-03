package com.hanghae99.airbnbclonebe.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestApiException {
    private int status;
    private String message;
}
