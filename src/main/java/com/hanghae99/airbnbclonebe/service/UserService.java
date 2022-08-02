package com.hanghae99.airbnbclonebe.service;

import com.hanghae99.airbnbclonebe.auth.auth.UserDetailsImpl;
import com.hanghae99.airbnbclonebe.dto.ResponseDto;
import com.hanghae99.airbnbclonebe.dto.SignupRequestDto;
import com.hanghae99.airbnbclonebe.dto.UserInfoDto;
import com.hanghae99.airbnbclonebe.model.User;
import com.hanghae99.airbnbclonebe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public ResponseDto signupUser(SignupRequestDto requestDto) {

        String username = requestDto.getUsername();
        User checkUser = userRepository.findByUsername(username);
        if (checkUser != null) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, password, "USER");
        userRepository.save(user);

        ResponseDto responseDto = new ResponseDto(true, "등록성공");
        return responseDto;
    }

    public UserInfoDto findUser(UserDetailsImpl userDetails) {

        User user = userDetails.getUser();
        List<String> roleList = user.getRoleList();
        boolean isHost = false;
        if (roleList.contains("HOST")) isHost = true;

        return new UserInfoDto(user.getUsername(), isHost);
    }

    public ResponseDto registerHost(UserDetailsImpl userDetails) {

        User user = userRepository.findByUsername(userDetails.getUsername());

        user.setRoles("HOST");
        userRepository.save(user);

        return new ResponseDto(true, "호스트 등록 성공");

    }
}
