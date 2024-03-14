package com.example.sparta.reviewtest.service;

import com.example.sparta.domain.review.dto.ReviewRequestDto;
import com.example.sparta.domain.review.dto.ReviewResponseDto;
import com.example.sparta.domain.review.entity.Review;
import com.example.sparta.domain.review.repository.ReviewRepository;
import com.example.sparta.domain.review.service.ReviewServiceImpl;
import com.example.sparta.domain.store.entity.Store;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ReviewServiceImplTest {

  @Mock
  ReviewRepository reviewRepository;

  @Autowired
  ReviewServiceImpl reviewServiceImpl;


  @Test
  @DisplayName("전체 리뷰조회")
  public void allReviews() {
    //given
    ReviewRequestDto reviewRequestDto = new ReviewRequestDto(4, "안녕하세요", 1L, 1L);
    //   User user = new User("박정섭","1234","eses565@naver.com","삼천포");
    Store store = new Store();
    //   Review review = new Review(reviewRequestDto, user, store);

    ReviewRequestDto reviewRequestDto2 = new ReviewRequestDto(4, "안녕하세요", 1L, 1L);
    //  User user2 = new User("김철수","1234","eses565@naver.com","삼천포");
    Store store2 = new Store();
    //   Review review2 = new Review(reviewRequestDto, user2, store);

    List<Review> lists = new ArrayList<>();
    //  lists.add(review);
    //   lists.add(review2);

    //given(reviewService.findAll()).willReturn(List<ReviewResponseDto>);

    //when
    List<ReviewResponseDto> all = reviewServiceImpl.findAll();

    //then
    Assertions.assertEquals(all.size(), 2);

  }

  @Test
  @DisplayName("단건 리뷰조회")
  public void getReview() {

    //given
    ReviewRequestDto reviewRequestDto = new ReviewRequestDto(4, "안녕하세요", 1L, 1L);
    //  User user = new User("박정섭","1234","eses565@naver.com","삼천포");
    Store store = new Store();
    // Review review = new Review(reviewRequestDto, user, store);

    ReviewRequestDto reviewRequestDto2 = new ReviewRequestDto(4, "안녕하세요", 1L, 1L);
    //  User user2 = new User("김철수","1234","eses565@naver.com","삼천포");
    Store store2 = new Store();
    //  Review review2 = new Review(reviewRequestDto, user2, store);

    List<Review> lists = new ArrayList<>();
    //  lists.add(review);
    //  lists.add(review2);
    ;
    //  ReviewResponseDto reviewResponseDto = new ReviewResponseDto(review);
    // given(reviewService.findOne(any())).willReturn(reviewResponseDto);

    //when
    ReviewResponseDto one = reviewServiceImpl.findOne(1L);

    //then
    System.out.println(one.getContent());
    //  System.out.println(reviewResponseDto.getContent());
    //Assertions.assertEquals(one.getRating(),reviewResponseDto.getRating());
  }

  @Test
  @DisplayName("리뷰 삭제")
  public void deleteReview() {

    //given

    //when

    //then
  }

  @Test
  @DisplayName("리뷰 수정")
  public void updateReview() {

    //given

    //when

    //then
  }

  @Test
  @DisplayName("리뷰 등록")
  public void createReview() {

    //given

    //when

    //then
  }
}
