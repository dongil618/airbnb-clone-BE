package com.hanghae99.airbnbclonebe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.hanghae99.airbnbclonebe.dto.ReservationRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Entity
@NoArgsConstructor
public class Reservation extends TimeStamped{

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

    //@JsonDeserialize(using= LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone = "Asia/Seoul")//shape = JsonFormat.Shape.STRING,
    @Column(nullable = false)
    private LocalDateTime checkIn;

    //@JsonDeserialize(using= LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone = "Asia/Seoul")//shape = JsonFormat.Shape.STRING,
    @Column(nullable = false)
    private LocalDateTime checkOut;

    @Column(nullable = false)
    private int guestNum;

    @Column(nullable = false)
    private int totalPrice;

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
