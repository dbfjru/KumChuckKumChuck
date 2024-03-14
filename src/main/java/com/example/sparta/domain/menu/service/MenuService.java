package com.example.sparta.domain.menu.service;

import com.example.sparta.domain.menu.dto.AddMenuRequestDto;
import com.example.sparta.domain.menu.dto.AddMenuResponseDto;
import com.example.sparta.domain.menu.dto.GetMenuResponseDto;

public interface MenuService {

  /**
   * 메뉴 조회
   *
   * @param storeId 조회할 가게 아이디
   * @param menuId  조회할 메뉴 아이디
   */
  public GetMenuResponseDto getMenu(Long storeId, Long menuId);

  /**
   * 메뉴 추가
   *
   * @param storeId    메뉴를 추가할 가게 아이디
   * @param requestDto 추가할 메뉴 정보(menuName, menuPrice) 내포
   */
  public AddMenuResponseDto addMenu(Long storeId, AddMenuRequestDto requestDto);

}
