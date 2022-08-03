package com.hanghae99.airbnbclonebe.dto;

import com.hanghae99.airbnbclonebe.model.Room;
import com.hanghae99.airbnbclonebe.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

//예약을 위해 필요한 정보
@Getter
@NoArgsConstructor//(access = AccessLevel.PRIVATE)
public class ReservationRequestDto {
    Long roomId;
    LocalDateTime checkIn;
    LocalDateTime checkOut;
    int guestNum;
    int totalPrice;
    //예약자 아이디는 파라미터로 받기

}
