package com.hanghae99.airbnbclonebe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GetReservationsResponseDto {

    private Long roomId;
    private String title;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private int guestNum;
    private int totalPrice;
    private String imgUrl;
    private String location;
    private Boolean isCancel;
    private Boolean isComplete;
}
