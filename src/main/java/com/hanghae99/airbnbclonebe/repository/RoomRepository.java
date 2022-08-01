package com.hanghae99.airbnbclonebe.repository;

import com.hanghae99.airbnbclonebe.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long>, RoomRepositoryCustom {

}
