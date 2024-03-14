package com.example.sparta.domain.store.controller;

import com.example.sparta.domain.store.dto.CreateStoreRequestDto;
import com.example.sparta.domain.store.dto.OpeningHoursDto;
import com.example.sparta.domain.store.dto.StoreRequestDto;
import com.example.sparta.domain.store.dto.StoreResponseDto;
import com.example.sparta.domain.store.service.StoreServiceImpl;
import com.example.sparta.global.aop.MyLogging;
import com.example.sparta.global.aop.MyPerfMeasure;
import com.example.sparta.global.dto.ResponseDto;
import com.example.sparta.global.impl.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@RequestMapping("/stores")
@MyLogging
@MyPerfMeasure
public class StoreController {

  private final StoreServiceImpl storeServiceImpl;

  @PostMapping()
  public ResponseEntity<ResponseDto<StoreResponseDto>> createStore(
      @RequestBody CreateStoreRequestDto storeRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ResponseDto
            .<StoreResponseDto>builder()
            .statusCode(201)
            .data(storeServiceImpl.createStore(storeRequestDto, userDetails.getUser()))
            .build());
  }

  @GetMapping
  public ResponseEntity<ResponseDto<List<StoreResponseDto>>> getAllStore() {

    return ResponseEntity.status(HttpStatus.OK)
        .body(ResponseDto
            .<List<StoreResponseDto>>builder()
            .statusCode(200)
            .data(storeServiceImpl.getAllStore())
            .build());
  }

  @PutMapping("/{storeId}")
  public ResponseEntity<ResponseDto<Long>> editStoreDetails(
      @PathVariable(name = "storeId") Long id,
      @RequestBody StoreRequestDto storeRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    return ResponseEntity.status(HttpStatus.OK)
        .body(ResponseDto
            .<Long>builder()
            .statusCode(200)
            .data(storeServiceImpl.editStore(id, storeRequestDto, userDetails.getUser()))
            .build());
  }

  @DeleteMapping("/{storeId}")
  public ResponseEntity<ResponseDto<Long>> deleteStore(@PathVariable(name = "storeId") Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    return ResponseEntity.status(HttpStatus.OK)
        .body(ResponseDto
            .<Long>builder()
            .statusCode(200)
            .data(storeServiceImpl.deleteStore(id, userDetails.getUser()))
            .build());

  }

  @GetMapping("/{storeId}")
  public ResponseEntity<ResponseDto<StoreResponseDto>> getStoreById(
      @PathVariable(name = "storeId") Long id) {

    return ResponseEntity.status(HttpStatus.OK)
        .body(ResponseDto
            .<StoreResponseDto>builder()
            .statusCode(200)
            .data(storeServiceImpl.getStoreById(id))
            .build());

  }

  //extra
  @GetMapping("/search")
  public ResponseEntity<ResponseDto<List<StoreResponseDto>>> getStoreByName(
      @RequestParam(name = "name") String name) {

    return ResponseEntity.status(HttpStatus.OK)
        .body(ResponseDto
            .<List<StoreResponseDto>>builder()
            .statusCode(200)
            .data(storeServiceImpl.getAllStoreByName(name))
            .build());
  }

  // 가계 주인 기능
  @PatchMapping("/{storeId}/open")
  public ResponseEntity<ResponseDto<Long>> openStore(@PathVariable(name = "storeId") Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    return ResponseEntity.status(HttpStatus.OK)
        .body(ResponseDto
            .<Long>builder()
            .statusCode(200)
            .data(storeServiceImpl.openStore(id, userDetails.getUser()))
            .build());
  }

  @PatchMapping("/{storeId}/close")
  public ResponseEntity<ResponseDto<Long>> closeStore(@PathVariable(name = "storeId") Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    return ResponseEntity.status(HttpStatus.OK)
        .body(ResponseDto
            .<Long>builder()
            .statusCode(200)
            .data(storeServiceImpl.closeStore(id, userDetails.getUser()))
            .build());
  }

  @PatchMapping("/{storeId}/openinghours")
  public ResponseEntity<ResponseDto<OpeningHoursDto>> updateStoreOpeningHours(
      @PathVariable(name = "storeId") Long id,
      @RequestBody OpeningHoursDto requestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    return ResponseEntity.status(HttpStatus.OK)
        .body(ResponseDto
            .<OpeningHoursDto>builder()
            .statusCode(200)
            .data(storeServiceImpl.updateOpeningHours(id, requestDto, userDetails.getUser()))
            .build());
  }

  //운영자 용 기능
  @PatchMapping("/{storeId}/status/force/{code}")
  public ResponseEntity<ResponseDto<StoreResponseDto>> forceChangeStoreStatus(
      @PathVariable(name = "storeId") Long id,
      @PathVariable(name = "code") int code,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    return ResponseEntity.status(HttpStatus.OK)
        .body(ResponseDto
            .<StoreResponseDto>builder()
            .statusCode(200)
            .data(storeServiceImpl.forceStatus(id, code, userDetails.getUser().getRole()))
            .build());
  }
}
