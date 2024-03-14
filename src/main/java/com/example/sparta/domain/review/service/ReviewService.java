package com.example.sparta.domain.review.service;

import com.example.sparta.domain.review.dto.ReviewRequestDto;
import com.example.sparta.domain.review.dto.ReviewResponseDto;
import com.example.sparta.domain.user.entity.User;
import java.util.List;

public interface ReviewService {

  /**
   * 리뷰 전체조회
   */
  public List<ReviewResponseDto> findAll();

  /**
   * 리뷰 단건 조회 리뷰가 존재하지 않으면 예외 발생
   *
   * @param id 조회 희망 리뷰 아이디
   */
  public ReviewResponseDto findOne(Long id);

  /**
   * 리뷰 수정
   *
   * @param id               수정 희망 리뷰 아이디
   * @param reviewRequestDto 수정할 리뷰 내용(rating, content, orderId, storeId) 내포
   * @param user             수정 유저
   */
  public ReviewResponseDto updateOne(Long id, ReviewRequestDto reviewRequestDto, User user);

  /**
   * 리뷰 등록
   *
   * @param reviewRequestDto 등록하고자 하는 리뷰 내용(rating, content, orderId, storeId) 내포
   * @param user             등록 유저
   */
  public ReviewResponseDto register(ReviewRequestDto reviewRequestDto, User user);

  /**
   * 리뷰 삭제
   *
   * @param id   리뷰 아이디
   * @param user 유저 정보
   */
  public void deleteOne(Long id, User user);
}
