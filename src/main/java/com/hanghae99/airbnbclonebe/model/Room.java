package com.hanghae99.airbnbclonebe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Room extends TimeStamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ROOM_ID")
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(nullable = false)
    private int maxGuest;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String information;

    @Column(nullable = false)
    private String category;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "room")
    private List<Option> optionList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "room")
    private List<Image> imageList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "room")
    private List<Wish> wishList = new ArrayList<>();

}
