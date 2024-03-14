package com.example.sparta.domain.orderdetail.service;

import com.example.sparta.domain.orderdetail.dto.GetOrderDetailResponseDto;
import com.example.sparta.domain.orderdetail.dto.OrderDetailRequestDto;
import com.example.sparta.domain.orderdetail.dto.OrderDetailResponseDto;
import com.example.sparta.domain.user.entity.User;

public interface OrderDetailService {

  /**
   * 주문 상세 추가
   *
   * @param requestDto 주문상세 요청(quantity) 내포
   * @param storeId    주문할 가게 아이디
   * @param menuId     선택한 메뉴 아이디
   * @param user       주문하는 유저
   */
  public OrderDetailResponseDto addOrderDetail(OrderDetailRequestDto requestDto,
      Long storeId, Long menuId, User user);

  /**
   * 주문 상세 조회
   *
   * @param user 조회 유저
   */
  public GetOrderDetailResponseDto getOrderDetail(User user);

  /**
   * 주문 상세 삭제
   *
   * @param orderDetailId 삭제할 주문상세 아이디
   * @param user          유저
   */
  public Long deleteOrderDetail(Long orderDetailId, User user);

  /**
   * 주문 상세에서 수량 변경
   *
   * @param orderDetailId 주문상세 아이디
   * @param quantity      변경 희망 수량
   * @param user          유저
   */
  public OrderDetailResponseDto updateOrderDetail(Long orderDetailId, Integer quantity,
      User user);
}
