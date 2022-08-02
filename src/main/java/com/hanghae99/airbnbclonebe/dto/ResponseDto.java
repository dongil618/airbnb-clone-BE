package com.hanghae99.airbnbclonebe.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDto {
    private boolean status;
    private String message;

    public ResponseDto(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
