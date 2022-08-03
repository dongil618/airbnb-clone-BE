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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public ResponseDto createReservation(ReservationRequestDto requestDto, Long roomId, User user){
        //room이 유효한지는 컨트롤러에서 검사한 후 서비스로 넘겨주기
        //user도 유효한지는 컨트롤러에서 검사한 후 서비스로 넘겨주기

        //username입력받아서 로그인 정보(=예약자정보) 불러오기, roomId 입력받아서 원하는 예약 방 찾기

        Room room=roomRepository.findRoomById(roomId).orElseThrow(()->new NullPointerException("해당 숙소가 존재하지 않습니다"));
        LocalDateTime checkIn=requestDto.getCheckIn();
        LocalDateTime checkOut=requestDto.getCheckOut();
        System.out.println("체크인: "+requestDto.getCheckIn());
        System.out.println("체크아웃: "+requestDto.getCheckOut());

        //예약이 가능한 날짜인지 확인
        boolean canReservate=checkDate(room,checkIn,checkOut);
        if(canReservate==false)
            return new ResponseDto(HttpStatus.BAD_REQUEST.value(),"등록실패.");
        else
        {
            Reservation reservation=new Reservation(requestDto,room,user);
            reservationRepository.save(reservation);
            return new ResponseDto(HttpStatus.OK.value(),"등록 성공");
        }

    }


    public boolean checkDate(Room room,LocalDateTime checkIn,LocalDateTime checkOut){
        List<Reservation> existReservation=reservationRepository.findAllByRoom(room);
        /*for(Reservation reservation:existReservation){
            System.out.println("저장된 체크인: "+reservation.getCheckIn()); //문제: 처음것만 저장됨ㅠㅠ
            System.out.println("저장된 체크아웃: "+reservation.getCheckOut());
        }*/
        for(Reservation reservation:existReservation){
            reservation.getCheckOut().plusDays(1); //마지막 날짜는 포함 안되므로 1 더해서

            if((checkIn.isAfter(reservation.getCheckIn())&&checkIn.isBefore(reservation.getCheckOut()))
                    ||(checkOut.isAfter(reservation.getCheckIn())&&checkOut.isBefore(reservation.getCheckOut()))){
                return false;
            }
        }
        if(checkIn.isAfter(checkOut))
            return false;

        return true;


    }



}
