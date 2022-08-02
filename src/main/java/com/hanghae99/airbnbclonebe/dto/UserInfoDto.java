package com.hanghae99.airbnbclonebe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDto {
    private String username;
    private boolean isHost;

    public UserInfoDto(String username, boolean isHost) {
        this.username = username;
        this.isHost = isHost;
    }
}
