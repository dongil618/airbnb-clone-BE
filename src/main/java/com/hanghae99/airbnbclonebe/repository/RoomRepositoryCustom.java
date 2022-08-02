package com.hanghae99.airbnbclonebe.repository;

import com.hanghae99.airbnbclonebe.dto.GetRoomsResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface RoomRepositoryCustom {
    Slice<GetRoomsResponseDto> findAllByCategoryOrderByCreatedAt(String category, Pageable pageable, Long userId);
    Slice<GetRoomsResponseDto> findAllByCategoryOrderByCreatedAtFilter(String category, Pageable pageable, Long userId, boolean parking, boolean kitchen, boolean aircon, boolean wifi, boolean washer, boolean tv, int minPrice, int maxPrice);
}
