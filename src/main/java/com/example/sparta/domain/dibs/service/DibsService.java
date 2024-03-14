package com.example.sparta.domain.dibs.service;

import com.example.sparta.domain.dibs.dto.DibsResponseDto;
import com.example.sparta.domain.user.entity.User;

public interface DibsService {

  /**
   * 찜 생성
   *
   * @param id   찜할 가게 아이디
   * @param user 유저
   */
  public DibsResponseDto createDibs(Long id, User user);

  /**
   * 찜 삭제
   *
   * @param storeId 삭제할 가게 아이디
   * @param user    유저
   */
  public long deleteDibs(Long storeId, User user);
}
