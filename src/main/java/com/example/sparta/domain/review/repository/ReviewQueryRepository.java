package com.example.sparta.domain.review.repository;

import static com.example.sparta.domain.review.entity.QReview.review;

import com.example.sparta.domain.order.entity.Order;
import com.example.sparta.domain.review.entity.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor

public class ReviewQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public Review findByOrder(Order order) {
    return jpaQueryFactory
        .selectFrom(review)
        .where(
            review.order.eq(order)
        )
        .fetchOne();
  }
}
