package com.hanghae99.airbnbclonebe.service;

import com.hanghae99.airbnbclonebe.model.Room;
import com.hanghae99.airbnbclonebe.model.User;
import com.hanghae99.airbnbclonebe.model.Wish;
import com.hanghae99.airbnbclonebe.repository.RoomRepository;
import com.hanghae99.airbnbclonebe.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;
    private final RoomRepository roomRepository;


    public void addWish(User user, Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(
                () -> new IllegalArgumentException("해당 숙소 정보를 찾을 수 없습니다.")
        );

        // 중복으로 wish 못하도록 중복확인
        if(wishRepository.findByUserAndRoom(user, room).isPresent()){
            throw new IllegalArgumentException("이미 wish를 등록한 숙소입니다.");
        }

        wishRepository.save(new Wish(user, room));
    }

    @Transactional
    public void deleteWish(User user, Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(
                () -> new IllegalArgumentException("해당 숙소 정보를 찾을 수 없습니다.")
        );

        Wish wish = wishRepository.findByUserAndRoom(user, room)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 wish 항목입니다."));

        wishRepository.deleteById(wish.getId());
    }
}
