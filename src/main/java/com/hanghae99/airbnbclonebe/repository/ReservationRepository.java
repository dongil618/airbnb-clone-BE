package com.hanghae99.airbnbclonebe.repository;

import com.hanghae99.airbnbclonebe.model.Reservation;
import com.hanghae99.airbnbclonebe.model.Room;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long>{
    List<Reservation> findAllByRoom(Room room);
    //예약 정보들을 userId로 찾기
    //모든 예약을 roomId로 찾기

    //해당 유저가 예약한 예약현황 보기
    //이 방에 예약된 날짜들을 확인하기 위해 roomId로 이 방의 모든 예약을 찾음
}
