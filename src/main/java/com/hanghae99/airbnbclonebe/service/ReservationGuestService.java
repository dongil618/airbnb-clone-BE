package com.hanghae99.airbnbclonebe.service;

import com.hanghae99.airbnbclonebe.auth.auth.UserDetailsImpl;
import com.hanghae99.airbnbclonebe.dto.ReservationRequestDto;
import com.hanghae99.airbnbclonebe.dto.ReservationResponseDto;
import com.hanghae99.airbnbclonebe.model.Reservation;
import com.hanghae99.airbnbclonebe.model.Room;
import com.hanghae99.airbnbclonebe.model.User;
import com.hanghae99.airbnbclonebe.repository.ReservationRepository;
import com.hanghae99.airbnbclonebe.repository.RoomRepository;
import com.hanghae99.airbnbclonebe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Service
public class ReservationGuestService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    //request(roomId,체크인,체크아웃,guestNum,totalPrice)
    // +룸아이디(request에서 추출)
    // +유저정보 받아서 예약 생성
    public Reservation createReservation(ReservationRequestDto requestDto, Long roomId, String username){
        //username입력받아서 로그인 정보(=예약자정보) 불러오기
        //room이 유효한지는 컨트롤러에서 검사한 후 서비스로 넘겨주기
        //user도 유효한지는 컨트롤러에서 검사한 후 서비스로 넘겨주기
        User user=userRepository.findByUsername(username).orElseThrow(()->new NullPointerException("해당 사용자를 찾을 수 없습니다."));
        Room room=roomRepository.findRoomById(roomId).orElseThrow(()->new NullPointerException("해당 숙소가 존재하지 않습니다"));

//        LocalDateTime checkIn=requestDto.getCheckIn();
//        LocalDateTime checkOut=requestDto.getCheckOut();
//        int guestNum= requestDto.getGuestNum();
//        int totalPrice= requestDto.getTotalPrice();

        Reservation reservation=new Reservation(requestDto,room,user);
        reservation=reservationRepository.save(reservation);
        return reservation;


    }

    public static Reservation toReservation(ReservationRequestDto requestDto,Room room,User user){
        return new Reservation(requestDto,room,user);
    }

}
