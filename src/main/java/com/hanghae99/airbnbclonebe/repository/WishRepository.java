package com.hanghae99.airbnbclonebe.repository;

import com.hanghae99.airbnbclonebe.model.Room;
import com.hanghae99.airbnbclonebe.model.User;
import com.hanghae99.airbnbclonebe.model.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {
    Optional<Wish> findByUserAndRoom(User user, Room room);
}
