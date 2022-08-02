package com.hanghae99.airbnbclonebe.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private Long id;
    private String username;
    private String password;
    private String roles;
}
