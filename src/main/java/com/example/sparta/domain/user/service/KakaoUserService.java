package com.example.sparta.domain.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface KakaoUserService {

  /**
   * @param code 카카오 API 인가 코드
   */
  public String kakaoLogin(String code) throws JsonProcessingException;

}
