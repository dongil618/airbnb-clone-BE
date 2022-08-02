package com.hanghae99.airbnbclonebe.controller;

import com.hanghae99.airbnbclonebe.auth.auth.UserDetailsImpl;
import com.hanghae99.airbnbclonebe.dto.ReservationRequestDto;
import com.hanghae99.airbnbclonebe.dto.ResponseDto;
import com.hanghae99.airbnbclonebe.dto.ResponseMessageDto;
import com.hanghae99.airbnbclonebe.repository.ReservationRepository;
import com.hanghae99.airbnbclonebe.repository.RoomRepository;
import com.hanghae99.airbnbclonebe.repository.UserRepository;
import com.hanghae99.airbnbclonebe.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReservationController {
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    public final ReservationService reservationService;

    //예약하기
    @PostMapping("/api/reservation")
    public ResponseDto createReservation(@RequestBody ReservationRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception{
       if(userDetails!=null)
       {
        String username=userDetails.getUser().getUsername();//로그인 정보를 확인하기 위한 username
        Long roomId=requestDto.getRoomId();
        return reservationService.createReservation(requestDto,roomId,username);}

       return new ResponseDto(false,"로그인이 필요합니다");
    }
}
