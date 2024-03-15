package com.example.sparta.domain.user.repository;

import static com.example.sparta.domain.user.entity.QUser.user;

import com.example.sparta.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {
  private final JPAQueryFactory jpaQueryFactory;

  public User findByEmail(String email){
    return jpaQueryFactory
        .selectFrom(user)
        .where(
            user.email.eq(email)
        )
        .fetchOne();
  }

  public User findByKakaoId(Long kakaoId){
    return jpaQueryFactory
        .selectFrom(user)
        .where(
            user.kakaoId.eq(kakaoId)
        )
        .fetchOne();
  }
}
