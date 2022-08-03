package com.hanghae99.airbnbclonebe.dto;

import com.hanghae99.airbnbclonebe.model.OptionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class RoomRequestDto {
    private String title;
    private List<String> imgUrl;
    private int price;
    private int maxGuest;
    private String information;
    private String location;
    private List<OptionEnum> option;
    private String category;
}
