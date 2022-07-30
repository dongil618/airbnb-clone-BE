package com.hanghae99.airbnbclonebe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Wish {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "WISH_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    public Wish(User user, Room room) {
        this.user = user;
        this.room = room;
    }
}
