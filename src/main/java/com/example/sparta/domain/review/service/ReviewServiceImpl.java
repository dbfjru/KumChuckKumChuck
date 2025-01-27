package com.example.sparta.domain.review.service;

import com.example.sparta.domain.order.entity.Order;
import com.example.sparta.domain.order.repository.OrderRepository;
import com.example.sparta.domain.review.dto.ReviewRequestDto;
import com.example.sparta.domain.review.dto.ReviewResponseDto;
import com.example.sparta.domain.review.entity.Review;
import com.example.sparta.domain.review.repository.ReviewQueryRepository;
import com.example.sparta.domain.review.repository.ReviewRepository;
import com.example.sparta.domain.store.entity.Store;
import com.example.sparta.domain.store.repository.StoreRepository;
import com.example.sparta.domain.user.entity.User;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;
  private final ReviewQueryRepository reviewQueryRepository;
  private final StoreRepository storeRepository;
  private final OrderRepository orderRepository;

  @Override
  public List<ReviewResponseDto> findAll() {
    return reviewRepository.findAll().stream().map(ReviewResponseDto::new).toList();
  }

  @Override
  public ReviewResponseDto findOne(Long id) {
    Review review = reviewRepository.findById(id)
        .orElseThrow(() -> new NullPointerException("리뷰가 존재하지 않습니다."));

    return new ReviewResponseDto(review);
  }

  @Override
  public ReviewResponseDto updateOne(Long id, ReviewRequestDto reviewRequestDto, User user) {

    if (reviewRequestDto.getRating() == null) {
      throw new IllegalArgumentException("Rating 올바른 값을 넣어주세요.");
    }
    Review review = reviewRepository.findById(id)
        .orElseThrow(() -> new NullPointerException("수정할 리뷰가 없습니다."));

    if (user.getUserId() == review.getReviewId()) { // 리뷰의 테이블에 있는 user_id를 가지고 와서 비교한 후에 검증한다.
      throw new IllegalArgumentException("리뷰 수정할 수 있는 권한이없습니다.");
    }
    review.updateOne(reviewRequestDto.getRating(), reviewRequestDto.getContent());
    Review reviewSave = reviewRepository.save(review);

    return new ReviewResponseDto(reviewSave);
  }

  @Override
  public ReviewResponseDto register(ReviewRequestDto reviewRequestDto, User user) {
    if (reviewRequestDto.getRating() == null) {
      throw new NullPointerException("Rating 올바른 값을 넣어주세요.");
    }

    if (reviewRequestDto.getRating() > 5) {
      throw new IllegalArgumentException("rating 6이상할 수 없습니다.");
    }

    Store store = storeRepository.getReferenceById(reviewRequestDto.getStoreId());

    Order order = orderRepository.findById(reviewRequestDto.getOrderId()).orElseThrow(
        () -> new NoSuchElementException("주문을 찾을 수 없습니다")
    );

    if (reviewQueryRepository.findByOrder(order) != null) {
      throw new IllegalArgumentException("이미 리뷰가 등록돼있습니다.");
    }

    Review review = new Review(reviewRequestDto, user, store, order);

    return new ReviewResponseDto(reviewRepository.save(review));
  }

  @Override
  public void deleteOne(Long id, User user) {
    Review review = reviewRepository.findById(id)
        .orElseThrow(() -> new NullPointerException("존재하지 않는 리뷰입니다"));

    if (!review.getUser().getUserId().equals(user.getUserId())) {
      throw new IllegalArgumentException("리뷰를 삭제할 수 있는 권한이 없습니다.");
    }

    reviewRepository.delete(review);
  }
}
