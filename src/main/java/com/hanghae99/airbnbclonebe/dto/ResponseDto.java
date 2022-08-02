package com.hanghae99.airbnbclonebe.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDto {
    private int status;
    private String message;

    public ResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
