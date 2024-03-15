package com.example.sparta.domain.orderdetail.repository;

import static com.example.sparta.domain.orderdetail.entity.QOrderDetail.orderDetail;

import com.example.sparta.domain.order.entity.Order;
import com.example.sparta.domain.orderdetail.entity.OrderDetail;
import com.example.sparta.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderDetailQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public List<OrderDetail> findAllByUserAndOrder(User user, Order order) {
    return jpaQueryFactory
        .selectFrom(orderDetail)
        .where(
            orderDetail.user.eq(user).and(orderDetail.order.eq(order))
        )
        .fetch();
  }

  public List<OrderDetail> findAllByUser(User user) {
    return jpaQueryFactory
        .selectFrom(orderDetail)
        .where(
            orderDetail.user.eq(user)
        )
        .fetch();
  }

  public List<OrderDetail> findAllByOrder(Order order) {
    return jpaQueryFactory
        .selectFrom(orderDetail)
        .where(
            orderDetail.order.eq(order)
        )
        .fetch();
  }
}
