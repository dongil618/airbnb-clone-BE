package com.hanghae99.airbnbclonebe.service;

import com.hanghae99.airbnbclonebe.dto.GetRoomsResponseDto;
import com.hanghae99.airbnbclonebe.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public Slice<GetRoomsResponseDto> getRooms(String category, Pageable pageable, Long userId) {
        return roomRepository.findAllByCategoryOrderByCreatedAt(category, pageable, userId);
    }

    public Slice<GetRoomsResponseDto> getRoomsFilter(String category, Pageable pageable, Long userId, boolean parking, boolean kitchen, boolean aircon, boolean wifi, boolean washer, boolean tv, int minPrice, int maxPrice) {
        return roomRepository.findAllByCategoryOrderByCreatedAtFilter(category, pageable, userId, parking, kitchen, aircon, wifi, washer, tv, minPrice, maxPrice);
    }
}
