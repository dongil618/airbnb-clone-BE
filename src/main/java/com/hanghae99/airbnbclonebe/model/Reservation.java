package com.hanghae99.airbnbclonebe.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hanghae99.airbnbclonebe.dto.ReservationRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.*;
import java.time.LocalDateTime;


@Getter
@Entity
@NoArgsConstructor
public class Reservation {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "RESERVATION_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(nullable = false)
    private LocalDateTime checkIn;

    @Column(nullable = false)
    private LocalDateTime checkOut;

    @Column(nullable = false)
    private int guestNum;

    @Column(nullable = false)
    private int totalPrice;

    @Column(nullable = false)
    private boolean status;//예약 상태(가능=TRUE,불가능=FALSE)

    @Column(nullable = false)
    private boolean isCancel;

    @Column(nullable = false)
    private boolean isComplete;

    //예약생성을 위한 생성자
    public Reservation(ReservationRequestDto requestDto,Room room,User user)
    {
        //예약 요청 requestDto를 받아서, 해당 룸, 해당 유저와 함께 저장
        this.checkIn=requestDto.getCheckIn();
        this.checkOut=requestDto.getCheckOut();
        this.guestNum=requestDto.getGuestNum();
        this.totalPrice=requestDto.getTotalPrice();
        this.room=room;
        this.user=user;
    }








}
