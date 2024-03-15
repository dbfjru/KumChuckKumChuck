package com.example.sparta.domain.dibs.service;

import com.example.sparta.domain.dibs.dto.DibsResponseDto;
import com.example.sparta.domain.dibs.entity.Dibs;
import com.example.sparta.domain.dibs.repository.DibsQueryRepository;
import com.example.sparta.domain.dibs.repository.DibsRepository;
import com.example.sparta.domain.store.entity.Store;
import com.example.sparta.domain.store.repository.StoreRepository;
import com.example.sparta.domain.user.entity.User;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DibsServiceImpl implements DibsService {

  private final StoreRepository storeRepository;
  private final DibsRepository dibsRepository;
  private final DibsQueryRepository dibsQueryRepository;

  @Override
  public DibsResponseDto createDibs(Long id, User user) {
    Store store = storeRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("해당 가계는 찾을수 없어요"));

    if (dibsQueryRepository.findDibsByStoreAndUser(store, user) != null) {
      throw new IllegalArgumentException("이미 찜한 가게입니다.");
    }

    try {
      Dibs dibs = new Dibs(user, store);
      dibsRepository.save(dibs);
      return new DibsResponseDto(dibs);
    } catch (Exception e) {
      throw new IllegalArgumentException("dibs 등록 실패");
    }
  }

  @Override
  public long deleteDibs(Long storeId, User user) {
    Store store = storeRepository.findById(storeId)
        .orElseThrow(() -> new NoSuchElementException("해당 가계는 찾을수 없어요"));

    try {
      Dibs dibs = dibsQueryRepository.findDibsByStoreAndUser(store, user);
      if (dibs == null) {
        throw new NoSuchElementException("해당 찜을 찾을수 없어요");
      }
      dibsRepository.delete(dibs);
      return storeId;
    } catch (Exception e) {
      throw new IllegalArgumentException("dibs 제거 실패");
    }
  }
}
