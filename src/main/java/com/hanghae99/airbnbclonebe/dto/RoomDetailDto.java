package com.hanghae99.airbnbclonebe.dto;

import com.hanghae99.airbnbclonebe.model.Image;
import com.hanghae99.airbnbclonebe.model.Option;
import com.hanghae99.airbnbclonebe.model.Room;
import com.hanghae99.airbnbclonebe.model.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class RoomDetailDto {

    private List<Image> imageList=new ArrayList<>();
    private List<Option> optionList=new ArrayList<>();//optionId를 받아야하나?
    private String title;
    private String hostname;//호스트
    private String userId;//호스트도 유저. 유저id로 호스트 찾기?
    private int maxGuest;
    private int price;
    private String information;
    private String location;
    private String category;

    public RoomDetailDto(Room room){
        this.title=room.getTitle();
        this.maxGuest=room.getMaxGuest();
        this.price= room.getPrice();
        this.information=room.getInformation();
        this.location=room.getLocation();
        this.category= room.getCategory();
        this.hostname=room.getUser().getUsername(); //room의 유저=호스트
        this.imageList=room.getImageList();
        this.optionList=room.getOptionList();
    }

}
