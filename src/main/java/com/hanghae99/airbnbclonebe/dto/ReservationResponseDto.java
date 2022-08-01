package com.hanghae99.airbnbclonebe.dto;

import com.hanghae99.airbnbclonebe.model.Reservation;

import java.time.LocalDateTime;

public class ReservationResponseDto {
    Long roomId;
    //Long userId;
    //예약자 아이디 필요? 노션에 DB에는 있고 api설계에는 없음
    //아 이거 걍 예약자 아이디 파라미터로 받아서 돌려주자
    LocalDateTime checkIn;
    LocalDateTime checkOut;
    int guestNum;
    int totalPrice;
    String location;
    Boolean isCancel;
    Boolean isComplete;

    public ReservationResponseDto(Reservation reservation){
        //this.reservation.getRoomId?
        //이렇게 하면 안될듯, 왜냐면 룸이 존재하는지 유효성 검사를 해줘야해서 받아온걸로 바로 roomId를 정하면 안됨
        this.roomId=roomId;
        //예약 request에 포함되는 항복들
        this.checkIn=reservation.getCheckIn();
        this.checkOut=reservation.getCheckOut();
        this.totalPrice=reservation.getTotalPrice();
        //예약 response에만 존재하는 항복들
        this.location=location;
        this.isCancel=isCancel;
        this.isComplete=isComplete;
    }

}
