package com.example.sparta.domain.store.entity;

import com.example.sparta.domain.store.dto.StoreRequestDto;
import com.example.sparta.global.entity.Timestamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "stores")
public class Store extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String address;

    @Column
    private String content;

    @Column(nullable = false)
    private Float rating;

    @Column(nullable = false)
    private Integer dibsCount;

    @Column(nullable = false)
    private Integer reviewCount;

    @Column
    private String deliveryAddress;

    public Store(StoreRequestDto requestDto){
        name = requestDto.getName();
        category = requestDto.getCategory();
        address = requestDto.getAddress();
        content = requestDto.getContent();
        rating = requestDto.getRating();
        dibsCount = requestDto.getDibsCount();
        reviewCount = requestDto.getReviewCount();
        deliveryAddress = requestDto.getDeliveryAddress();
    }
    @Transactional
    public void update(StoreRequestDto requestDto) {
        name = requestDto.getName();
        category = requestDto.getCategory();
        address = requestDto.getAddress();
        content = requestDto.getContent();
        rating = requestDto.getRating();
        dibsCount = requestDto.getDibsCount();
        reviewCount = requestDto.getReviewCount();
        deliveryAddress = requestDto.getDeliveryAddress();
    }
}