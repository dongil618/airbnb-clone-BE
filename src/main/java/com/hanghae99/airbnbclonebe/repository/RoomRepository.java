package com.hanghae99.airbnbclonebe.repository;

import com.hanghae99.airbnbclonebe.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, RoomRepositoryCustom {

    List<Room> findAllByUserId(Long id);
    Optional<Room> findRoomById(Long roomId);
}