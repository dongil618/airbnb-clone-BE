package com.hanghae99.airbnbclonebe.service;

import com.hanghae99.airbnbclonebe.dto.GetRoomsResponseDto;
import com.hanghae99.airbnbclonebe.dto.RoomDetailDto;
import com.hanghae99.airbnbclonebe.model.Image;
import com.hanghae99.airbnbclonebe.model.Option;
import com.hanghae99.airbnbclonebe.model.Room;
import com.hanghae99.airbnbclonebe.repository.ImageRepository;
import com.hanghae99.airbnbclonebe.repository.OptionRepository;
import com.hanghae99.airbnbclonebe.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RoomService {


    private final RoomRepository roomRepository;
    private final ImageRepository imageRepository;
    private final OptionRepository optionRepository;

    public Slice<GetRoomsResponseDto> getRooms(String category, Pageable pageable, Long userId) {
        return roomRepository.findAllByCategoryOrderByCreatedAt(category, pageable, userId);
    }

    public Slice<GetRoomsResponseDto> getRoomsFilter(String category, Pageable pageable, Long userId, boolean parking, boolean kitchen, boolean aircon, boolean wifi, boolean washer, boolean tv, int minPrice, int maxPrice) {
        return roomRepository.findAllByCategoryOrderByCreatedAtFilter(category, pageable, userId, parking, kitchen, aircon, wifi, washer, tv, minPrice, maxPrice);
    }

    public RoomDetailDto getRoomDetail(Long roomId){
        System.out.println(roomId);
        Room room=roomRepository.findById(roomId)
        .orElseThrow(()->new IllegalArgumentException("숙소가 존재하지 않습니다"));
        //String hostname=room.getUser().getUsername();
        //roomId로 호스트(유저) 찾는걸 해야하나?
        List<String> imgUrl = new ArrayList<>();
        List<Image> imageList=imageRepository.findAllByRoom(room);
        for(Image image : imageList){
            imgUrl.add(image.getImgUrl());
        }

        List<String> option = new ArrayList<>();
        List<Option> optionList=optionRepository.findAllByRoom(room);
        for(Option optionName : optionList){
            option.add(String.valueOf(optionName.getName()));
        }
        return new RoomDetailDto(room,imgUrl,option);
    }
}
