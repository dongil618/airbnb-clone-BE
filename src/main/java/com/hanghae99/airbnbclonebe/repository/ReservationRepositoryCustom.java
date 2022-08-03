package com.hanghae99.airbnbclonebe.repository;

import com.hanghae99.airbnbclonebe.dto.GetReservationsResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ReservationRepositoryCustom {

    Slice<GetReservationsResponseDto> findAllByUserAndRoomOrderByCreatedAt(Pageable pageable, Long userId);
}
