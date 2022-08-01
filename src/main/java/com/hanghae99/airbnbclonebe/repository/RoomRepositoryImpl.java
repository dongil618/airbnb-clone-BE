package com.hanghae99.airbnbclonebe.repository;

import com.hanghae99.airbnbclonebe.dto.GetRoomsResponseDto;
import com.hanghae99.airbnbclonebe.model.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.ComparableExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class RoomRepositoryImpl implements RoomRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private QRoom room = QRoom.room;
    private QWish wish = QWish.wish;
    private QImage image = QImage.image;

    private QOption option = QOption.option;

    @Override
    public Slice<GetRoomsResponseDto> findAllByCategoryOrderByCreatedAt(String category, Pageable pageable, Long userId) {
        List<GetRoomsResponseDto> returnRoom = queryFactory.select(Projections.fields(
                        GetRoomsResponseDto.class,
                        room.id.as("roomId"),
                        room.title,
                        room.price,
                        room.location,
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
                        ),
                        // wish 여부
                        new CaseBuilder()
                                .when(wish.id.isNull())
                                .then((ComparableExpression<Boolean>) Expressions.asBoolean(false))
                                .otherwise(Expressions.asBoolean(true)).as("isWish")
                ))
                .distinct()
                .from(room)
                .leftJoin(wish)
                .on(room.id.eq(wish.room.id), wish.user.id.eq(userId))
                .where(getCategory(category))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new SliceImpl<>(returnRoom, pageable, returnRoom.iterator().hasNext());
    }

    @Override
    public Slice<GetRoomsResponseDto> findAllByCategoryOrderByCreatedAtFilter(String category,
                                                                              Pageable pageable,
                                                                              Long userId,
                                                                              boolean parking,
                                                                              boolean kitchen,
                                                                              boolean aircon,
                                                                              boolean wifi,
                                                                              boolean washer,
                                                                              boolean tv,
                                                                              int minPrice,
                                                                              int maxPrice) {
        List<GetRoomsResponseDto> returnRoom = queryFactory.select(Projections.fields(
                        GetRoomsResponseDto.class,
                        room.id.as("roomId"),
                        room.title,
                        room.price,
                        room.location,
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
                        ),
                        // wish 여부
                        new CaseBuilder()
                                .when(wish.id.isNull())
                                .then((ComparableExpression<Boolean>) Expressions.asBoolean(false))
                                .otherwise(Expressions.asBoolean(true)).as("isWish")
                ))
                .distinct()
                .from(room)
                .leftJoin(wish)
                .on(room.id.eq(wish.room.id), wish.user.id.eq(userId))
                .leftJoin(option)
                .on(room.id.eq(option.room.id))
                .where(getCategory(category))
                .where(room.price.between(minPrice, maxPrice))
                .where(eqOptionBuilder(parking, kitchen, aircon, wifi, washer, tv))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new SliceImpl<>(returnRoom, pageable, returnRoom.iterator().hasNext());
    }

    private BooleanExpression getCategory(String category) {
        return category.equals("all") ? null : room.category.eq(category);
    }



    private BooleanBuilder eqOptionBuilder(boolean parking,
                                     boolean kitchen,
                                     boolean aircon,
                                     boolean wifi,
                                     boolean washer,
                                     boolean tv) {
        BooleanBuilder builder = new BooleanBuilder();
        if(parking){
            builder.or(option.name.eq(OptionEnum.valueOf("PARKING")));
        }
        if(kitchen){
            builder.or(option.name.eq(OptionEnum.valueOf("KITCHEN")));
        }
        if (aircon){
            builder.or(option.name.eq(OptionEnum.valueOf("AIRCON")));
        }
        if (wifi){
            builder.or(option.name.eq(OptionEnum.valueOf("WIFI")));
        }
        if (washer){
            builder.or(option.name.eq(OptionEnum.valueOf("WASHER")));
        }
        if (tv){
            builder.or(option.name.eq(OptionEnum.valueOf("TV")));
        }

        return builder;
    }

}
    
