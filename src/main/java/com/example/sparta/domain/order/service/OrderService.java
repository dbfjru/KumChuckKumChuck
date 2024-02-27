package com.example.sparta.domain.order.service;

import com.example.sparta.domain.order.dto.CreateOrderRequestDto;
import com.example.sparta.domain.order.dto.ModifyOrderRequestDto;
import com.example.sparta.domain.order.dto.OrderResponseDto;
import com.example.sparta.domain.order.entity.Order;
import com.example.sparta.domain.order.repository.OrderRepository;
import com.example.sparta.domain.orderdetail.entity.OrderDetail;
import com.example.sparta.domain.orderdetail.repository.OrderDetailRepository;
import com.example.sparta.domain.store.entity.Store;
import com.example.sparta.domain.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Transactional
    public OrderResponseDto createOrder(CreateOrderRequestDto requestDto, User user) {
        List<OrderDetail> orderDetailList = orderDetailRepository.findAllByUserAndOrder(user, null);
        if(orderDetailList.isEmpty()){
            throw new IllegalArgumentException("주문 실패 : 주문할 메뉴를 골라주세요.");
        }
        Long totalPrice = 0L;
        List<Long> orderDetailIdList = new ArrayList<>();
        List<String> menuNameList = new ArrayList<>();
        List<Integer> menuQuantityList = new ArrayList<>();
        Store store = orderDetailList.get(0).getStore();
        for (OrderDetail orderDetail : orderDetailList) {
            totalPrice += orderDetail.getMenu().getPrice() * orderDetail.getQuantity();
            orderDetailIdList.add(orderDetail.getOrderDetailId());
            menuNameList.add(orderDetail.getMenu().getName());
            menuQuantityList.add(orderDetail.getQuantity());
        }
        Order order = new Order(totalPrice, requestDto.getRequests(), 0, user, store);
        Order savedOrder = orderRepository.save(order);

        for (OrderDetail orderDetail : orderDetailList) { //orderDetail 에 주문 넣어주기
            orderDetail.setOrder(savedOrder);
        }
        return new OrderResponseDto(savedOrder, orderDetailIdList, menuNameList, menuQuantityList);
    }

    public OrderResponseDto getOrder(User user, Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문번호입니다."));
        return orderResponseDtoMaker(order);
    }

    public List<OrderResponseDto> getOrderList(User user) {
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        List<Order> orderList = orderRepository.findByUser(user);
        if (orderList.isEmpty()) {
            throw new NoSuchElementException("주문내역이 존재하지 않습니다.");
        }
        for (Order order : orderList) {
            orderResponseDtoList.add(orderResponseDtoMaker(order));
        }
        return orderResponseDtoList;
    }

    private OrderResponseDto orderResponseDtoMaker(Order order) {

        List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrder(order);
        List<Long> orderDetailIdList = new ArrayList<>();
        List<String> menuNameList = new ArrayList<>();
        List<Integer> menuQuantityList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetailList) {
            orderDetailIdList.add(orderDetail.getOrderDetailId());
            menuNameList.add(orderDetail.getMenu().getName());
            menuQuantityList.add(orderDetail.getQuantity());
        }
        return new OrderResponseDto(order, orderDetailIdList, menuNameList, menuQuantityList);
    }

    public Long deleteOrder(User user, Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));
        if (!order.getUser().getUserId().equals(user.getUserId())) {
            throw new IllegalArgumentException("주문 삭제 권한이 없습니다.");
        }
        orderRepository.delete(order);
        return order.getOrderId();
    }

    @Transactional
    public OrderResponseDto modifyOrderRequest(User user, Long orderId,
        ModifyOrderRequestDto modifyOrderRequestDto) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));
        if (!user.getUserId().equals(order.getUser().getUserId())) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        order.setRequests(modifyOrderRequestDto.getRequests());
        return orderResponseDtoMaker(order);
    }

}
