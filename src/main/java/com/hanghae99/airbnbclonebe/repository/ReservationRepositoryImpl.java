package com.hanghae99.airbnbclonebe.repository;

import com.hanghae99.airbnbclonebe.dto.GetReservationsResponseDto;
import com.hanghae99.airbnbclonebe.model.*;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    private QRoom room = QRoom.room;

    private QImage image = QImage.image;
    private QReservation reservation = QReservation.reservation;

    @Override
    public Slice<GetReservationsResponseDto> findAllByUserAndRoomOrderByCreatedAt(Pageable pageable, Long userId) {
        List<GetReservationsResponseDto> returnReservation = queryFactory.select(Projections.fields(
                GetReservationsResponseDto.class,
                        room.id.as("roomId"),
                        reservation.checkIn,
                        reservation.checkOut,
                        reservation.guestNum,
                        reservation.totalPrice,
                        room.location,
                        reservation.isCancel,
                        reservation.isComplete,
                        // imgUrl 1개만 가져오기  room.imageList.get(0) 오류남
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(image.imgUrl)
                                        .from(image)
                                        .where(image.id.eq(
                                                JPAExpressions
                                                        .select(image.id.min())
                                                        .from(image)
                                                        .where(room.id.eq(image.room.id))
                                        ))
                                ,"imgUrl"
                        )
                ))
                .from(reservation)
                .join(room)
                .on(reservation.room.id.eq(room.id))
                .orderBy(reservation.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new SliceImpl<>(returnReservation, pageable, returnReservation.iterator().hasNext());

    }
}
