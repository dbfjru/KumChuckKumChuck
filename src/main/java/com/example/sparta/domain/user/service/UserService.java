package com.example.sparta.domain.user.service;

import com.example.sparta.domain.user.dto.UserLoginRequestDto;
import com.example.sparta.domain.user.dto.UserPasswordUpdateRequestDto;
import com.example.sparta.domain.user.dto.UserProfileUpdateRequestDto;
import com.example.sparta.domain.user.dto.UserProfileUpdateResponseDto;
import com.example.sparta.domain.user.dto.UserSignupRequestDto;
import com.example.sparta.domain.user.dto.UserSignupResponseDto;
import com.example.sparta.domain.user.entity.User;
import jakarta.servlet.http.HttpServletResponse;


public interface UserService {

  /**
   * 회원가입 기능
   *
   * @param userSignupRequestDto 회원가입에 필요한 정보(name, password, email, address) 내포
   */
  public UserSignupResponseDto userSignup(UserSignupRequestDto userSignupRequestDto);

  /**
   * 로그인 기능
   *
   * @param userLoginRequestDto 로그인에 필요한 정보(email, password) 내포
   */
  public void userLogin(UserLoginRequestDto userLoginRequestDto,
      HttpServletResponse httpServletResponse);

  /**
   * 로그아웃 기능
   *
   * @param httpServletResponse 응답에 JWT 토큰을 null로 반환
   */
  public void userLogout(HttpServletResponse httpServletResponse);

  /**
   * 유저 프로필 수정 기능
   *
   * @param userProfileUpdateRequestDto 수정할 정보(name,address) 내포
   * @param user                        수정할 유저
   */

  public UserProfileUpdateResponseDto userProfileUpdate(
      UserProfileUpdateRequestDto userProfileUpdateRequestDto,
      User user);

  /**
   * 유저 프로필 수정 기능
   *
   * @param userPasswordUpdateRequestDto 수정 비밀번호 정보(password, newPassword, checkPassword) 내포
   * @param user                         수정할 유저
   */

  public void userPasswordUpdate(UserPasswordUpdateRequestDto userPasswordUpdateRequestDto,
      User user);
}
