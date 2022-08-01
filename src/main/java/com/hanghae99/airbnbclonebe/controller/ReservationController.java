package com.hanghae99.airbnbclonebe.controller;

import com.hanghae99.airbnbclonebe.auth.auth.UserDetailsImpl;
import com.hanghae99.airbnbclonebe.dto.ReservationRequestDto;
import com.hanghae99.airbnbclonebe.dto.ReservationResponseDto;
import com.hanghae99.airbnbclonebe.model.Reservation;
import com.hanghae99.airbnbclonebe.model.Room;
import com.hanghae99.airbnbclonebe.repository.ReservationRepository;
import com.hanghae99.airbnbclonebe.repository.RoomRepository;
import com.hanghae99.airbnbclonebe.repository.UserRepository;
import com.hanghae99.airbnbclonebe.service.ReservationGuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ReservationController {
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    public final ReservationGuestService reservationService;

    //예약하기
    @PostMapping("/api/reservation")
    public Reservation createReservation(@RequestBody ReservationRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String username=userDetails.getUser().getUsername();//로그인 정보를 확인하기 위한 username
        //Optional<Room> room=roomRepository.findRoomById(requestDto.getRoomId());//request에 있는 룸아이디로 룸 팢기
        Long roomId=requestDto.getRoomId();
        //예약생성->request정보(roomId,체크인,체크아웃,guestNum,totalPrice)+roomId(request에서 추출)+username(예약자id)
        Reservation reservation=reservationService.createReservation(requestDto,roomId,username);
        return reservation;

    }
}
