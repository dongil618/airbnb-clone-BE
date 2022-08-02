package com.hanghae99.airbnbclonebe.service;

import com.hanghae99.airbnbclonebe.dto.ReservationRequestDto;
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
    public void createReservation(ReservationRequestDto requestDto, Long roomId, String username){
        //room이 유효한지는 컨트롤러에서 검사한 후 서비스로 넘겨주기
        //user도 유효한지는 컨트롤러에서 검사한 후 서비스로 넘겨주기

        //username입력받아서 로그인 정보(=예약자정보) 불러오기, roomId 입력받아서 원하는 예약 방 찾기
        User user=userRepository.findByUsername(username);
        Room room=roomRepository.findRoomById(roomId).orElseThrow(()->new NullPointerException("해당 숙소가 존재하지 않습니다"));
        LocalDate checkIn=requestDto.getCheckIn();
        LocalDate checkOut=requestDto.getCheckOut();

        //예약이 가능한 날짜인지 확인
        boolean canReservate=checkDate(roomId,checkIn,checkOut);
        if(canReservate==false)
            throw new NullPointerException("예약이 불가능한 날짜입니다.");
        else
        {
            System.out.println("예약성공!");
            Reservation reservation=new Reservation(requestDto,room,user);
            reservation=reservationRepository.save(reservation);
            //return reservation;

        }

    }

    public boolean checkDate(Long roomId,LocalDate checkIn,LocalDate checkOut){
        List<Reservation> existReservation=reservationRepository.findAllById(roomId);
        for(Reservation reservation:existReservation){
            if((checkIn.isAfter(reservation.getCheckIn())&&checkIn.isBefore(reservation.getCheckOut()))
                    ||(checkOut.isAfter(reservation.getCheckIn())&&checkIn.isBefore(reservation.getCheckOut())))
            {
                return false;
            }
        }
        return true;
    }



}
