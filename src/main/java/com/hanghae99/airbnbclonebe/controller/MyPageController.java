package com.hanghae99.airbnbclonebe.controller;

import com.hanghae99.airbnbclonebe.auth.auth.UserDetailsImpl;
import com.hanghae99.airbnbclonebe.dto.GetReservationsResponseDto;
import com.hanghae99.airbnbclonebe.dto.GetRoomsResponseDto;
import com.hanghae99.airbnbclonebe.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mypage")
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/wishlist")
    public Slice<GetRoomsResponseDto> getWishList(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                  Pageable pageable){

        Long userId = userDetails.getUser().getId();

        return myPageService.getWishList(pageable, userId);
    }

    @GetMapping("/reservation")
    public Slice<GetReservationsResponseDto> getReservations(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                             Pageable pageable){

        Long userId = userDetails.getUser().getId();

        return myPageService.getReservations(pageable, userId);
    }
}
