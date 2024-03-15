package com.example.sparta.domain.order.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.example.sparta.domain.order.dto.CreateOrderRequestDto;
import com.example.sparta.domain.order.repository.OrderRepository;
import com.example.sparta.domain.orderdetail.entity.OrderDetail;
import com.example.sparta.domain.orderdetail.repository.OrderDetailQueryRepository;
import com.example.sparta.domain.orderdetail.repository.OrderDetailRepository;
import com.example.sparta.domain.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

  @InjectMocks
  OrderServiceImpl orderServiceImpl;
  @Mock
  OrderRepository orderRepository;
  @Mock
  OrderDetailRepository orderDetailRepository;
  @Mock
  OrderDetailQueryRepository orderDetailQueryRepository;

  @DisplayName("주문생성 - 실패")
  @Test
  void createOrder_fail() {
    //given
    User testUser = new User();
    CreateOrderRequestDto testRequestDto = new CreateOrderRequestDto("test request");
    List<OrderDetail> orderDetailList = new ArrayList<>();
    given(orderDetailQueryRepository.findAllByUserAndOrder(testUser, null)).willReturn(
        orderDetailList);

    //when then
    assertThrows(IllegalArgumentException.class,
        () -> orderServiceImpl.createOrder(testRequestDto, testUser));
  }


}