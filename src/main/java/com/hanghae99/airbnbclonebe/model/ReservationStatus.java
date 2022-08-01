package com.hanghae99.airbnbclonebe.model;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    RESERVATION_OK,//예약완료
    RESERVATION_NO,//예약없음
    COMPLETED//여행완료?
}
