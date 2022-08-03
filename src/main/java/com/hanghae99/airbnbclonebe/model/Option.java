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
public class Option {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING) // 이 어노테이션이 없으면 숫자로 나옴
    private OptionEnum name;

    public Option(Room room, OptionEnum name) {
        this.room = room;
        this.name = name;
    }
}
