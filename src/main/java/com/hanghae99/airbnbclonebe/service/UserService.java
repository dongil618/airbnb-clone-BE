package com.hanghae99.airbnbclonebe.service;

import com.hanghae99.airbnbclonebe.auth.auth.UserDetailsImpl;
import com.hanghae99.airbnbclonebe.dto.ResponseDto;
import com.hanghae99.airbnbclonebe.dto.RoomRequestDto;
import com.hanghae99.airbnbclonebe.dto.SignupRequestDto;
import com.hanghae99.airbnbclonebe.dto.UserInfoDto;
import com.hanghae99.airbnbclonebe.model.*;
import com.hanghae99.airbnbclonebe.repository.ImageRepository;
import com.hanghae99.airbnbclonebe.repository.OptionRepository;
import com.hanghae99.airbnbclonebe.repository.RoomRepository;
import com.hanghae99.airbnbclonebe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final OptionRepository optionRepository;
    private final RoomRepository roomRepository;
    private final ImageRepository imageRepository;



    public ResponseDto signupUser(SignupRequestDto requestDto) {

        String username = requestDto.getUsername();
        User checkUser = userRepository.findByUsername(username);
        if (checkUser != null) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, password, "USER");
        userRepository.save(user);

        return new ResponseDto(HttpStatus.OK.value(), "등록성공");
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

        return new ResponseDto(HttpStatus.OK.value(), "호스트 등록 성공");
    }

    public ResponseDto registerRoom(UserDetailsImpl userDetails, RoomRequestDto requestDto) {

        // 해당 User가 Host role을 가지고 있는지 확인
        User user = userDetails.getUser();
        String roles = user.getRoles();
        if(roles.equals("HOST")){
            // Room Save
            Room room = new Room(requestDto, user);
            roomRepository.save(room);

            // Option Save
            List<Option> optionList = new ArrayList<>();

            for(OptionEnum name : requestDto.getOption()){
                Option option = new Option(room, name);
                optionList.add(option);
            }
            optionRepository.saveAll(optionList);

            // Image Save
            List<Image> imageList = new ArrayList<>();
            for(String imgUrl : requestDto.getImgUrl()){
                Image image = new Image(room, imgUrl);
                imageList.add(image);
            }
            imageRepository.saveAll(imageList);

            return new ResponseDto(HttpStatus.OK.value(), "숙소 등록 성공");
        }


        // User가 Host role을 가지고 있지 않다면
        return new ResponseDto(HttpStatus.BAD_REQUEST.value(), "Host로 등록되어있지 않습니다.");
    }
}
