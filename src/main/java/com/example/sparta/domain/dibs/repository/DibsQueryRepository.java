package com.example.sparta.domain.dibs.repository;

import static com.example.sparta.domain.dibs.entity.QDibs.dibs;

import com.example.sparta.domain.dibs.entity.Dibs;
import com.example.sparta.domain.store.entity.Store;
import com.example.sparta.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DibsQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public Dibs findDibsByStoreAndUser(Store store, User user) {
    return jpaQueryFactory
        .selectFrom(dibs)
        .where(
            dibs.store.eq(store).and(dibs.user.eq(user))
        )
        .fetchOne();
  }
}
