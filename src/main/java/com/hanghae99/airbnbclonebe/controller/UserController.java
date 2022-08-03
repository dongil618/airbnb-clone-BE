package com.hanghae99.airbnbclonebe.controller;

import com.hanghae99.airbnbclonebe.auth.auth.UserDetailsImpl;
import com.hanghae99.airbnbclonebe.dto.ResponseDto;
import com.hanghae99.airbnbclonebe.dto.RoomRequestDto;
import com.hanghae99.airbnbclonebe.dto.SignupRequestDto;
import com.hanghae99.airbnbclonebe.dto.UserInfoDto;
import com.hanghae99.airbnbclonebe.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/signup")
    public ResponseDto signup(@RequestBody SignupRequestDto requestDto) {
        return userService.signupUser(requestDto);
    }

    @PostMapping("/api/info")
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.findUser(userDetails);
    }

    @PostMapping("/api/host/register")
    public ResponseDto registerHost(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.registerHost(userDetails);
    }

    @PostMapping("/api/host/room")
    public ResponseDto registerRoom(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody RoomRequestDto requestDto){
        return userService.registerRoom(userDetails, requestDto);
    }
}
