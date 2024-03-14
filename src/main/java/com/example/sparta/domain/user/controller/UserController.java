package com.example.sparta.domain.user.controller;


import com.example.sparta.domain.user.dto.UserLoginRequestDto;
import com.example.sparta.domain.user.dto.UserPasswordUpdateRequestDto;
import com.example.sparta.domain.user.dto.UserProfileUpdateRequestDto;
import com.example.sparta.domain.user.dto.UserSignupRequestDto;
import com.example.sparta.domain.user.service.KakaoUserServiceImpl;
import com.example.sparta.domain.user.service.UserServiceImpl;
import com.example.sparta.global.aop.MyLogging;
import com.example.sparta.global.dto.ResponseDto;
import com.example.sparta.global.impl.UserDetailsImpl;
import com.example.sparta.global.jwt.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
@MyLogging
public class UserController {

  //Service 주입받기
  private final UserServiceImpl userServiceImpl;

  //JwtUtil 주입받기
  private final JwtUtil jwtUtil;
  private final KakaoUserServiceImpl kakaoUserService;

  // 회원 가입 하기
  @PostMapping("/signup")
  @ResponseBody
  public ResponseEntity<ResponseDto<Void>> userSignup(
      @Valid @RequestBody UserSignupRequestDto userSignupRequestDto) {

    userServiceImpl.userSignup(userSignupRequestDto);

    return ResponseEntity.status(201).body(ResponseDto.
        <Void>builder()
        .statusCode(HttpStatus.CREATED.value())
        .data(null)
        .build()
    );
  }

  // 로그인 하기
  @PostMapping("/login")
  @ResponseBody
  public ResponseEntity<ResponseDto<Void>> userLogin(
      @RequestBody UserLoginRequestDto userLoginRequestDto,
      HttpServletResponse httpServletResponse) {

    userServiceImpl.userLogin(userLoginRequestDto, httpServletResponse);

    return ResponseEntity.status(200).body(ResponseDto.<Void>builder()
        .statusCode(HttpStatus.OK.value())
        .data(null)
        .build()
    );
  }


  //로그아웃 하기
  @PostMapping("/logout")
  @ResponseBody
  public ResponseEntity<ResponseDto<Void>> userLogout(HttpServletResponse httpServletResponse) {

    userServiceImpl.userLogout(httpServletResponse);

    return ResponseEntity.status(200).body(ResponseDto.<Void>builder()
        .statusCode(HttpStatus.OK.value())
        .data(null)
        .build()
    );
  }

  //유저 정보 수정하기 (이름, 주소)
  @PatchMapping
  @ResponseBody
  public ResponseEntity<ResponseDto<Void>> userProfileUpdate(
      @RequestBody UserProfileUpdateRequestDto userProfileUpdateRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    userServiceImpl.userProfileUpdate(userProfileUpdateRequestDto, userDetails.getUser());

    return ResponseEntity.status(200).body(ResponseDto.<Void>builder()
        .statusCode(HttpStatus.OK.value())
        .data(null)
        .build()
    );
  }

  //유저 정보 수정하기 (비밀번호)
  @PatchMapping("/password")
  @ResponseBody
  public ResponseEntity<ResponseDto<Void>> userPasswordUpdate(
      @RequestBody UserPasswordUpdateRequestDto userPasswordUpdateRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    userServiceImpl.userPasswordUpdate(userPasswordUpdateRequestDto, userDetails.getUser());

    return ResponseEntity.status(200).body(ResponseDto.<Void>builder()
        .statusCode(HttpStatus.OK.value())
        .data(null)
        .build()
    );
  }

  // 카카오 로그인
  @GetMapping("/kakao")
  public String kakaoLogin(@RequestParam String code, HttpServletResponse httpServletResponse)
      throws JsonProcessingException {

    String token = kakaoUserService.kakaoLogin(code);   // jwt 토큰을 쿠키에 넣어주는 작업 해서 response 에 넣어줌
    Cookie cookie = new Cookie(jwtUtil.AUTHORIZATION_HEADER, token);
    cookie.setPath("/");
    httpServletResponse.addCookie(cookie);

    return "redirect:/";
  }
}