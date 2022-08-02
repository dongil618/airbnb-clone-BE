package com.hanghae99.airbnbclonebe.controller;

import com.hanghae99.airbnbclonebe.auth.auth.UserDetailsImpl;
import com.hanghae99.airbnbclonebe.dto.ResponseDto;
import com.hanghae99.airbnbclonebe.model.User;
import com.hanghae99.airbnbclonebe.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishController {

    private final WishService wishService;

    @PostMapping("/{roomId}")
    public ResponseDto addWish(@AuthenticationPrincipal UserDetailsImpl userDetails,
                            @PathVariable Long roomId){
        // 사용자만 저장 가능.
        User user = userDetails.getUser();
        return wishService.addWish(user, roomId);
    }

    @DeleteMapping("/{roomId}")
    public ResponseDto deleteWish(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                  @PathVariable Long roomId){
        // 사용자만 삭제 가능.
        User user = userDetails.getUser();
        return wishService.deleteWish(user, roomId);
    }
}
