package com.hanghae99.airbnbclonebe.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
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

    @Column(nullable = false)
    private LocalDateTime checkIn;

    @Column(nullable = false)
    private LocalDateTime checkOut;

    @Column(nullable = false)
    private int GuestNum;

    @Column(nullable = false)
    private int totalPrice;

    @Column(nullable = false)
    private boolean isCancel;

    @Column(nullable = false)
    private boolean isComplete;

}
