package com.hanghae99.airbnbclonebe.service;

import com.hanghae99.airbnbclonebe.dto.GetRoomsResponseDto;
import com.hanghae99.airbnbclonebe.dto.RoomDetailDto;
import com.hanghae99.airbnbclonebe.model.Room;
import com.hanghae99.airbnbclonebe.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;


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
    public RoomDetailDto getRoomDetail(Long roomid){
        System.out.println(roomid);
        Room room=roomRepository.findById(roomid)
        .orElseThrow(()->new IllegalArgumentException("숙소가 존재하지 않습니다"));
        //String hostname=room.getUser().getUsername();
        //roomId로 호스트(유저) 찾는걸 해야하나?
        RoomDetailDto roomDetailDto=new RoomDetailDto(room);
        return roomDetailDto;
    }
}
