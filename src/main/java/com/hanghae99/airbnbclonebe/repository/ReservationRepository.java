package com.hanghae99.airbnbclonebe.repository;

import com.hanghae99.airbnbclonebe.model.Reservation;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findAllById(Long userId);
    //예약 정보들을 userId로 찾기
    //해당 유저가 예약한 예약현황 보기

}
