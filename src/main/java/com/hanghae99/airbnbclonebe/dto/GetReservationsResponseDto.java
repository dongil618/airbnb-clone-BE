package com.hanghae99.airbnbclonebe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class GetReservationsResponseDto {

    private Long roomId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int guestNum;
    private int totalPrice;
    private String imgUrl;
    private String location;
    private Boolean isCancel;
    private Boolean isComplete;
    private Boolean status;
}
