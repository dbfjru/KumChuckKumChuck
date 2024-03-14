package com.example.sparta.domain.order.controller;

import com.example.sparta.domain.order.dto.CreateOrderRequestDto;
import com.example.sparta.domain.order.dto.ModifyOrderRequestDto;
import com.example.sparta.domain.order.dto.OrderResponseDto;
import com.example.sparta.domain.order.service.OrderServiceImpl;
import com.example.sparta.global.aop.MyLogging;
import com.example.sparta.global.aop.MyPerfMeasure;
import com.example.sparta.global.dto.ResponseDto;
import com.example.sparta.global.impl.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@MyLogging
@MyPerfMeasure
public class OrderController {

  private final OrderServiceImpl orderServiceImpl;

  @PostMapping
  public ResponseEntity<ResponseDto<OrderResponseDto>> createOrder(
      @RequestBody CreateOrderRequestDto requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    OrderResponseDto orderResponseDto;

    orderResponseDto = orderServiceImpl.createOrder(requestDto,
        userDetails.getUser());

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ResponseDto.<OrderResponseDto>builder()
            .statusCode(HttpStatus.CREATED.value())
            .data(orderResponseDto)
            .build());
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<ResponseDto<OrderResponseDto>> getOrder(
      @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long orderId) {

    OrderResponseDto orderResponseDto;

    orderResponseDto = orderServiceImpl.getOrder(userDetails.getUser(), orderId);

    return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.<OrderResponseDto>builder()
        .statusCode(HttpStatus.OK.value())
        .data(orderResponseDto)
        .build()
    );
  }

  @GetMapping
  public ResponseEntity<ResponseDto<List<OrderResponseDto>>> getOrderList(
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    List<OrderResponseDto> orderResponseDtoList;

    orderResponseDtoList = orderServiceImpl.getOrderList(userDetails.getUser());

    return ResponseEntity.ok().body(ResponseDto.<List<OrderResponseDto>>builder()
        .statusCode(HttpStatus.OK.value())
        .data(orderResponseDtoList)
        .build()
    );
  }

  @DeleteMapping("/{orderId}")
  public ResponseEntity<ResponseDto<Void>> deleteOrder(
      @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long orderId) {

    orderServiceImpl.deleteOrder(userDetails.getUser(), orderId);

    return ResponseEntity.ok().body(ResponseDto.<Void>builder()
        .statusCode(HttpStatus.OK.value())
        .data(null)
        .build()
    );
  }

  @PatchMapping("/{orderId}")
  public ResponseEntity<ResponseDto<OrderResponseDto>> modifyOrderRequest(
      @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long orderId,
      @RequestBody ModifyOrderRequestDto modifyOrderRequestDto) {

    OrderResponseDto orderResponseDto;

    orderResponseDto = orderServiceImpl.modifyOrderRequest(userDetails.getUser(),
        orderId, modifyOrderRequestDto);

    return ResponseEntity.ok().body(ResponseDto.<OrderResponseDto>builder()
        .statusCode(HttpStatus.OK.value())
        .data(orderResponseDto)
        .build()
    );
  }
}
