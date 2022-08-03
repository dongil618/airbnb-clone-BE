package com.hanghae99.airbnbclonebe.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private List<Image> imageList;
    @JsonIgnore
    private List<Option> optionList;//optionId를 받아야하나?
    private String title;
    private String hostname;//호스트
    private int maxGuest;
    private int price;
    private String information;
    private String location;
    private String category;

    public RoomDetailDto(Room room,List<Image> image,List<Option> option){
        this.title=room.getTitle();
        this.maxGuest=room.getMaxGuest();
        this.price= room.getPrice();
        this.information=room.getInformation();
        this.location=room.getLocation();
        this.category= room.getCategory();
        this.hostname=room.getUser().getUsername(); //room의 유저=호스트
        this.imageList=image;
        this.optionList=option;
    }



}
