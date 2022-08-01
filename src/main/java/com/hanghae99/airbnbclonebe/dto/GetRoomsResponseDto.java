package com.hanghae99.airbnbclonebe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class GetRoomsResponseDto {

    private Long roomId;
    private String title;
    private String imgUrl;
    private String location;
    private int price;
    private boolean isWish;
}
