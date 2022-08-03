package com.hanghae99.airbnbclonebe.repository;

import com.hanghae99.airbnbclonebe.model.Image;
import com.hanghae99.airbnbclonebe.model.Option;
import com.hanghae99.airbnbclonebe.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findAllByRoom(Room room);

}
