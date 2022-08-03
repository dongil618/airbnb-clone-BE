package com.hanghae99.airbnbclonebe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WishListResponseDto {
    private Long roomId;
    private String title;
    private String imgUrl;
    private String location;
    private String hostname;
    private int price;
    private Boolean isWish;
}
