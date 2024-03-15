package com.example.sparta.domain.store.repository;

import static com.example.sparta.domain.store.entity.QStore.store;

import com.example.sparta.domain.store.entity.Store;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StoreQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public List<Store> findAllByNameContains(String name) {
    return jpaQueryFactory
        .selectFrom(store)
        .where(
            store.name.contains(name)
        )
        .fetch();
  }
}
