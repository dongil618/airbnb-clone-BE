package com.hanghae99.airbnbclonebe.service;


import com.hanghae99.airbnbclonebe.dto.GetReservationsResponseDto;
import com.hanghae99.airbnbclonebe.dto.GetRoomsResponseDto;
import com.hanghae99.airbnbclonebe.dto.WishListResponseDto;
import com.hanghae99.airbnbclonebe.repository.ReservationRepository;
import com.hanghae99.airbnbclonebe.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public Slice<WishListResponseDto> getWishList(Pageable pageable, Long userId) {
        return roomRepository.findAllByOrderByCreatedAt(pageable, userId);
    }

    public Slice<GetReservationsResponseDto> getReservations(Pageable pageable, Long userId) {
        return reservationRepository.findAllByUserAndRoomOrderByCreatedAt(pageable, userId);
    }
}
