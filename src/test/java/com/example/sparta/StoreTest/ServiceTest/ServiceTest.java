package com.example.sparta.StoreTest.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.example.sparta.domain.store.dto.CreateStoreRequestDto;
import com.example.sparta.domain.store.dto.OpeningHoursDto;
import com.example.sparta.domain.store.dto.StoreRequestDto;
import com.example.sparta.domain.store.dto.StoreResponseDto;
import com.example.sparta.domain.store.entity.Store;
import com.example.sparta.domain.store.repository.StoreQueryRepository;
import com.example.sparta.domain.store.repository.StoreRepository;
import com.example.sparta.domain.store.service.StoreServiceImpl;
import com.example.sparta.domain.user.entity.User;
import com.example.sparta.domain.user.entity.UserRoleEnum;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ServiceTest {

  @Mock
  StoreRepository mockStoreRepository;
  @Mock
  StoreQueryRepository mockStoreQueryRepository;

  @Test
  @DisplayName("스토어 생성")
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

    //score
    Store store = new Store(requestDto, user);

    //service
    StoreServiceImpl storeServiceImpl = new StoreServiceImpl(mockStoreRepository,
        mockStoreQueryRepository);

    // when
    given(mockStoreRepository.save(any())).willReturn(store);

    //then
    StoreResponseDto storeResponseDto = storeServiceImpl.createStore(requestDto, user);

    assertEquals(store.getName(), storeResponseDto.getName());
  }

  @Test
  @DisplayName("스토어 리스트 가져오기")
  void test2() {
    //given

    // 유저
    User user = new User();
    user.setUserId(1L);
    user.setAddress("로마");
    user.setEmail("sparta@iscool.com");
    user.setName("스탄이");

    //ls
    List<Store> ls = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      StoreRequestDto s = new StoreRequestDto();
      s.setName("name is " + i);
      ls.add(new Store(s, user));
    }

    //service
    StoreServiceImpl storeServiceImpl = new StoreServiceImpl(mockStoreRepository,
        mockStoreQueryRepository);

    // when
    given(mockStoreRepository.findAll()).willReturn(ls);

    //then
    List<StoreResponseDto> storeResponseDto = storeServiceImpl.getAllStore();

    assertEquals(storeResponseDto.size(), ls.size());
  }

  @Test
  @DisplayName("스토어 수정 하기")
  void test3() {
    //given
    //스토어 request
    StoreRequestDto requestDto = new StoreRequestDto();
    requestDto.setName("new name");
    requestDto.setCategory("Chicken");
    requestDto.setDibsCount(100);
    requestDto.setRating(5.5f);

    // 유저
    User user = new User();
    user.setUserId(1L);
    user.setAddress("로마");
    user.setEmail("sparta@iscool.com");
    user.setName("스탄이");

    //score
    Store score = new Store(requestDto, user);

    //service
    StoreServiceImpl storeServiceImpl = new StoreServiceImpl(mockStoreRepository,
        mockStoreQueryRepository);

    // when
    given(mockStoreRepository.findById(1L)).willReturn(Optional.of(score));
    Long id = storeServiceImpl.editStore(1L, requestDto, user);
    //then
    assertEquals(1L, id);
  }

  @Test
  @DisplayName("스토어 삭제 하기")
  void test4() {
    //given
    //스토어 request
    StoreRequestDto requestDto = new StoreRequestDto();
    requestDto.setName("new name");
    requestDto.setCategory("Chicken");
    requestDto.setDibsCount(100);
    requestDto.setRating(5.5f);
    // 유저
    User user = new User();
    user.setUserId(1L);
    user.setAddress("로마");
    user.setEmail("sparta@iscool.com");
    user.setName("스탄이");
    //score
    Store score = new Store(requestDto, user);
    //service
    StoreServiceImpl storeServiceImpl = new StoreServiceImpl(mockStoreRepository,
        mockStoreQueryRepository);

    // when
    given(mockStoreRepository.findById(1L)).willReturn(Optional.of(score));
    Long id = storeServiceImpl.deleteStore(1L, user);
    //then
    assertEquals(1L, id);
  }

  @Test
  @DisplayName("스토어 선택 검색 하기")
  void test6() {
    //given
    //스토어 request
    StoreRequestDto requestDto = new StoreRequestDto();
    requestDto.setName("new name");
    requestDto.setCategory("Chicken");
    requestDto.setDibsCount(100);
    requestDto.setRating(5.5f);

    // 유저
    User user = new User();
    user.setUserId(1L);
    user.setAddress("로마");
    user.setEmail("sparta@iscool.com");
    user.setName("스탄이");

    //score
    Store score = new Store(requestDto, user);

    //service
    StoreServiceImpl storeServiceImpl = new StoreServiceImpl(mockStoreRepository,
        mockStoreQueryRepository);

    // when
    given(mockStoreRepository.findById(1L)).willReturn(Optional.of(score));
    StoreResponseDto storeResponseDto = storeServiceImpl.getStoreById(1L);
    //then
    assertEquals(storeResponseDto.getName(), score.getName());
  }

  @Test
  @DisplayName("스토어 이름으로 검색 하기")
  void test7() {
    //given
    //스토어 request
    StoreRequestDto requestDto = new StoreRequestDto();
    requestDto.setName("new name");
    requestDto.setCategory("Chicken");
    requestDto.setDibsCount(100);
    requestDto.setRating(5.5f);

    // 유저
    User user = new User();
    user.setUserId(1L);
    user.setAddress("로마");
    user.setEmail("sparta@iscool.com");
    user.setName("스탄이");

    //score
    Store score = new Store(requestDto, user);

    //ls
    List<Store> ls = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      ls.add(score);
    }

    //service
    StoreServiceImpl storeServiceImpl = new StoreServiceImpl(mockStoreRepository,
        mockStoreQueryRepository);

    // when
    given(mockStoreQueryRepository.findAllByNameContains("new name")).willReturn(ls);
    List<StoreResponseDto> storeResponseDtoLs = storeServiceImpl.getAllStoreByName("new name");
    //then
    assertEquals(storeResponseDtoLs.get(1).getName(), ls.get(1).getName());
  }

  @Test
  @DisplayName("영업 시작 + 영업 종료 하기")
  void test8() {
    //given

    StoreRequestDto requestDto = new StoreRequestDto();
    requestDto.setName("new name");
    requestDto.setCategory("Chicken");
    // 유저
    User user = new User();
    user.setUserId(1L);
    user.setAddress("로마");
    user.setEmail("sparta@iscool.com");
    user.setName("스탄이");

    //service
    StoreServiceImpl storeServiceImpl = new StoreServiceImpl(mockStoreRepository,
        mockStoreQueryRepository);

    //score
    Store score = new Store(requestDto, user);

    // when
    given(mockStoreRepository.findById(1L)).willReturn(Optional.of(score));
    Long id = storeServiceImpl.openStore(1L, user);
    Long id2 = storeServiceImpl.closeStore(1L, user);
    //then
    assertEquals(1L, id);
    assertEquals(1L, id2);
  }

  @Test
  @DisplayName("영업 시간 추가")
  void test9() {
    //given

    StoreRequestDto requestDto = new StoreRequestDto();
    requestDto.setName("new name");
    requestDto.setCategory("Chicken");
    // 유저
    User user = new User();
    user.setUserId(1L);
    user.setAddress("로마");
    user.setEmail("sparta@iscool.com");
    user.setName("스탄이");

    OpeningHoursDto dto = new OpeningHoursDto();
    dto.setOpening(LocalTime.now());
    dto.setClosing(LocalTime.MIDNIGHT);
    //service
    StoreServiceImpl storeServiceImpl = new StoreServiceImpl(mockStoreRepository,
        mockStoreQueryRepository);

    //score
    Store score = new Store(requestDto, user);

    // when
    given(mockStoreRepository.findById(1L)).willReturn(Optional.of(score));
    OpeningHoursDto ans = storeServiceImpl.updateOpeningHours(1L, dto, user);

    //then
    assertEquals(LocalTime.MIDNIGHT, ans.getClosing());
  }

  @Test
  @DisplayName("관리자 권한으로 가게 강제 관리")
  void test10() {
    //given

    StoreRequestDto requestDto = new StoreRequestDto();
    requestDto.setName("new name");
    requestDto.setCategory("Chicken");
    // 유저
    User user = new User();
    user.setUserId(1L);
    user.setRole(UserRoleEnum.ADMIN);

    OpeningHoursDto dto = new OpeningHoursDto();
    dto.setOpening(LocalTime.now());
    dto.setClosing(LocalTime.MIDNIGHT);
    //service
    StoreServiceImpl storeServiceImpl = new StoreServiceImpl(mockStoreRepository,
        mockStoreQueryRepository);

    //score
    Store store = new Store(requestDto, user);

    // when
    given(mockStoreRepository.findById(1L)).willReturn(Optional.of(store));
    StoreResponseDto ans = storeServiceImpl.forceStatus(1L, 1, user.getRole());

    //then
    assertEquals(store.getName(), ans.getName());
  }
}


