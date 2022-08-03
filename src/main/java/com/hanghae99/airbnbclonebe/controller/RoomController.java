package com.hanghae99.airbnbclonebe.controller;

import com.hanghae99.airbnbclonebe.auth.auth.UserDetailsImpl;
import com.hanghae99.airbnbclonebe.dto.GetRoomsResponseDto;
import com.hanghae99.airbnbclonebe.dto.RoomDetailDto;
import com.hanghae99.airbnbclonebe.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RoomController {

    private final RoomService roomService;

   @GetMapping("/rooms")
   public Slice<GetRoomsResponseDto> getRooms(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                               @RequestParam String category,
                                               Pageable pageable){
        // 로그인이 된 사람의 wish도 같이 보여줘야 하기 때문
        Long userId = userDetails.getUser().getId();

        return roomService.getRooms(category, pageable, userId);
   }

    @GetMapping("/room")
    public Slice<GetRoomsResponseDto> getRoomsFilter(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                               @RequestParam String category,
                                               @RequestParam(required = false) boolean parking,
                                               @RequestParam(required = false) boolean kitchen,
                                               @RequestParam(required = false) boolean aircon,
                                               @RequestParam(required = false) boolean wifi,
                                               @RequestParam(required = false) boolean washer,
                                               @RequestParam(required = false) boolean tv,
                                               @RequestParam(required = false) int minPrice,
                                               @RequestParam(required = false) int maxPrice,
                                               Pageable pageable){
        // 로그인이 된 사람의 wish도 같이 보여줘야 하기 때문
        Long userId = userDetails.getUser().getId();

        return roomService.getRoomsFilter(category, pageable, userId, parking, kitchen, aircon, wifi, washer, tv, minPrice, maxPrice);
    }

    @GetMapping("/api/room/{roomid}")
    public RoomDetailDto getRoomDetail(@PathVariable Long roomid){
        System.out.println("상세페이지를 찾습니다"+"룸아이디: "+roomid);
        RoomDetailDto roomDetailDto=roomService.getRoomDetail(roomid);
        return roomDetailDto;
    }

}
