package com.hanghae99.airbnbclonebe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Image {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IMAGE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @Column(nullable = false)
    private String imgUrl;

    public Image(Room room, String imgUrl) {
        this.room = room;
        this.imgUrl = imgUrl;
    }
}
