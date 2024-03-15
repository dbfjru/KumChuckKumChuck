package com.example.sparta.domain.order.repository;

import static com.example.sparta.domain.order.entity.QOrder.order;

import com.example.sparta.domain.order.entity.Order;
import com.example.sparta.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public List<Order> findByUser(User user) {
    return jpaQueryFactory
        .selectFrom(order)
        .where(order.user.eq(user))
        .fetch();
  }
}
