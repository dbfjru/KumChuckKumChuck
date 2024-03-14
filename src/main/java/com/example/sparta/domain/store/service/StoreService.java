package com.example.sparta.domain.store.service;

import com.example.sparta.domain.store.dto.CreateStoreRequestDto;
import com.example.sparta.domain.store.dto.OpeningHoursDto;
import com.example.sparta.domain.store.dto.StoreRequestDto;
import com.example.sparta.domain.store.dto.StoreResponseDto;
import com.example.sparta.domain.user.entity.User;
import com.example.sparta.domain.user.entity.UserRoleEnum;
import java.util.List;

public interface StoreService {

  /**
   * 가게 생성
   *
   * @param requestDto 가게의 name, category, address, content, deliveryAddress 내포
   * @param user       가게의 주인이 될 User 정보
   */
  public StoreResponseDto createStore(CreateStoreRequestDto requestDto, User user);

  /**
   * 모든 가게 목록 조회
   */
  public List<StoreResponseDto> getAllStore();

  /**
   * 가게 수정
   *
   * @param id              수정할 가게의 ID
   * @param storeRequestDto 수정할 정보 (name, category, address, content, rating, dibsCount,
   *                        reviewCount, deliveryAddress)
   * @param user            수정할 가게의 주인 유저
   */
  public Long editStore(Long id, StoreRequestDto storeRequestDto, User user);

  /**
   * 가게 삭제
   *
   * @param id   삭제할 가게 ID
   * @param user 삭제할 가게의 주인
   */
  public Long deleteStore(Long id, User user);

  /**
   * 특정 가게 조회
   *
   * @param id 특정 가게의 ID
   */
  public StoreResponseDto getStoreById(Long id);

  /**
   * 가게의 이름으로 조회
   *
   * @param name 조회할 가게 이름
   */
  public List<StoreResponseDto> getAllStoreByName(String name);

  /**
   * 가게 영업 시작 버튼
   *
   * @param id   가게 아이디
   * @param user 가게 주인
   */
  public Long openStore(Long id, User user);

  /**
   * 가게 영업 종료 버튼
   *
   * @param id   가게 아이디
   * @param user 가게 주인
   */
  public Long closeStore(Long id, User user);

  /**
   * 가게 영업 시작 시간 변경
   *
   * @param id   가게 아이디
   * @param dto  가게 영업시간 변경정보(opening, closing) 내포
   * @param user 가게 주인
   */
  public OpeningHoursDto updateOpeningHours(Long id, OpeningHoursDto dto, User user);

  /**
   * 관리자 권한 가게 상태 강제 변경
   *
   * @param id   변경할 가게 아이디
   * @param code 변경할 가게 상태 (0~5 가능)
   * @param role 권한 확인
   */
  public StoreResponseDto forceStatus(Long id, int code, UserRoleEnum role);
}
