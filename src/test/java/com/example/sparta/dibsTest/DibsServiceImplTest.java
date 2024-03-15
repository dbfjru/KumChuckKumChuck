package com.example.sparta.dibsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.example.sparta.domain.dibs.dto.DibsResponseDto;
import com.example.sparta.domain.dibs.entity.Dibs;
import com.example.sparta.domain.dibs.repository.DibsQueryRepository;
import com.example.sparta.domain.dibs.repository.DibsRepository;
import com.example.sparta.domain.dibs.service.DibsServiceImpl;
import com.example.sparta.domain.store.dto.CreateStoreRequestDto;
import com.example.sparta.domain.store.entity.Store;
import com.example.sparta.domain.store.repository.StoreRepository;
import com.example.sparta.domain.user.entity.User;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DibsServiceImplTest {

  @Mock
  DibsRepository mockdibsRepository;
  @Mock
  DibsQueryRepository mockdibsQueryRepository;
  @Mock
  StoreRepository mockStoreRepository;

  @Test
  @DisplayName("찜 생성")
  void test1() {

    //given
    //스토어 request
    CreateStoreRequestDto requestDto = new CreateStoreRequestDto();
    requestDto.setName("service name");
    requestDto.setCategory("Chicken");

    // 유저
    User user = new User();
    user.setUserId(1L);
    user.setAddress("로마");
    user.setEmail("sparta@iscool.com");
    user.setName("스탄이");

    //score for given
    Store store = new Store(requestDto, user);
    //dips for given
    Dibs dibs = new Dibs(user, store);

    //when
    DibsServiceImpl service = new DibsServiceImpl(mockStoreRepository, mockdibsRepository,
        mockdibsQueryRepository);

    given(mockStoreRepository.findById(1L)).willReturn(Optional.of(store));

    DibsResponseDto answer = service.createDibs(1L, user);

    //then
    assertEquals(dibs.getUser().getName(), answer.getUserName());
  }

  @Test
  @DisplayName("찜 제거")
  void test2() {
    //given
    //스토어 request
    CreateStoreRequestDto requestDto = new CreateStoreRequestDto();
    requestDto.setName("service name");
    requestDto.setCategory("Chicken");

    // 유저
    User user = new User();
    user.setUserId(1L);
    user.setAddress("로마");
    user.setEmail("sparta@iscool.com");
    user.setName("스탄이");

    //score for given
    Store store = new Store(requestDto, user);
    //dibs for given
    Dibs dibs = new Dibs(user, store);
    //when
    DibsServiceImpl service = new DibsServiceImpl(mockStoreRepository, mockdibsRepository,
        mockdibsQueryRepository);

    given(mockStoreRepository.findById(1L)).willReturn(Optional.of(store));
    given(mockdibsQueryRepository.findDibsByStoreAndUser(any(), any())).willReturn(
        dibs);

    Long ans = service.deleteDibs(1L, user);

    //then
    assertEquals(ans, 1L);

  }
}
