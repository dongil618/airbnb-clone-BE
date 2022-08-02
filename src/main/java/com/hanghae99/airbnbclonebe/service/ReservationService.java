package com.hanghae99.airbnbclonebe.service;

import com.hanghae99.airbnbclonebe.dto.ReservationRequestDto;
import com.hanghae99.airbnbclonebe.dto.ResponseDto;
import com.hanghae99.airbnbclonebe.dto.ResponseMessageDto;
import com.hanghae99.airbnbclonebe.model.Reservation;
import com.hanghae99.airbnbclonebe.model.Room;
import com.hanghae99.airbnbclonebe.model.User;
import com.hanghae99.airbnbclonebe.repository.ReservationRepository;
import com.hanghae99.airbnbclonebe.repository.RoomRepository;
import com.hanghae99.airbnbclonebe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ReservationService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    //request(roomId,체크인,체크아웃,guestNum,totalPrice)
    // +룸아이디(request에서 추출)
    // +유저정보 받아서 예약 생성
    public ResponseDto createReservation(ReservationRequestDto requestDto, Long roomId, String username){
        //room이 유효한지는 컨트롤러에서 검사한 후 서비스로 넘겨주기
        //user도 유효한지는 컨트롤러에서 검사한 후 서비스로 넘겨주기

        //username입력받아서 로그인 정보(=예약자정보) 불러오기, roomId 입력받아서 원하는 예약 방 찾기
        User user=userRepository.findByUsername(username);
        Room room=roomRepository.findRoomById(roomId).orElseThrow(()->new NullPointerException("해당 숙소가 존재하지 않습니다"));
        LocalDate checkIn=requestDto.getCheckIn();
        LocalDate checkOut=requestDto.getCheckOut();
        System.out.println("체크인: "+requestDto.getCheckIn());
        System.out.println("체크아웃: "+requestDto.getCheckOut());

        //예약이 가능한 날짜인지 확인
        boolean canReservate=checkDate(roomId,checkIn,checkOut);
        if(canReservate==false)
            return new ResponseDto(false,"등록실패.");
        else
        {
            Reservation reservation=new Reservation(requestDto,room,user);
            reservationRepository.save(reservation);
            return new ResponseDto(true,"등록 성공");
        }

    }

    public boolean checkDate(Long roomId,LocalDate checkIn,LocalDate checkOut){
        List<Reservation> existReservation=reservationRepository.findAllById(roomId);
        for(Reservation reservation:existReservation){
            if((checkIn.isAfter(reservation.getCheckIn())&&checkIn.isBefore(reservation.getCheckOut()))
                    ||(checkOut.isAfter(reservation.getCheckIn())&&checkIn.isBefore(reservation.getCheckOut())))
                return false;
        }
        if(checkIn.isAfter(checkOut))
            return false;

        return true;
    }



}
