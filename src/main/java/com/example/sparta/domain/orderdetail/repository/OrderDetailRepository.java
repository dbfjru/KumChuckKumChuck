package com.example.sparta.domain.orderdetail.repository;

import com.example.sparta.domain.orderdetail.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
