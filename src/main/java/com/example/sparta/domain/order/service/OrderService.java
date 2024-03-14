package com.example.sparta.domain.order.service;

import com.example.sparta.domain.order.dto.CreateOrderRequestDto;
import com.example.sparta.domain.order.dto.ModifyOrderRequestDto;
import com.example.sparta.domain.order.dto.OrderResponseDto;
import com.example.sparta.domain.user.entity.User;
import java.util.List;

public interface OrderService {

  /**
   * 주문 생성
   *
   * @param requestDto 주문 요청 사항
   * @param user       주문하는 유저
   */
  public OrderResponseDto createOrder(CreateOrderRequestDto requestDto, User user);

  /**
   * 주문 단건 조회
   *
   * @param user    조회하는 유저
   * @param orderId 조회할 주문 아이디
   */
  public OrderResponseDto getOrder(User user, Long orderId);

  /**
   * 주문 목록 조회
   *
   * @param user 조회하는 유저
   */
  public List<OrderResponseDto> getOrderList(User user);

  /**
   * 주문 삭제
   *
   * @param user    삭제하는 유저
   * @param orderId 삭제할 주문 아이디
   */
  public Long deleteOrder(User user, Long orderId);

  /**
   * 주문 수정
   *
   * @param user                  접근 유저
   * @param orderId               수정할 주문 아이디
   * @param modifyOrderRequestDto 변경 요청 사항
   */
  public OrderResponseDto modifyOrderRequest(User user, Long orderId,
      ModifyOrderRequestDto modifyOrderRequestDto);

}
