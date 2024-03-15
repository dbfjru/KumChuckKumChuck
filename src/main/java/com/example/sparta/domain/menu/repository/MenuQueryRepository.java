package com.example.sparta.domain.menu.repository;

import static com.example.sparta.domain.menu.entity.QMenu.menu;

import com.example.sparta.domain.menu.entity.Menu;
import com.example.sparta.domain.store.entity.Store;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MenuQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public Menu findByMenuNameAndStore(String menuName, Store store) {
    return jpaQueryFactory
        .selectFrom(menu)
        .where(
            menu.name.eq(menuName).and(menu.store.eq(store))
        )
        .fetchOne();
  }
}
