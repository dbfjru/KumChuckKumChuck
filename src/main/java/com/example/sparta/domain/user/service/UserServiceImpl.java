package com.example.sparta.domain.user.service;


import com.example.sparta.domain.user.dto.UserLoginRequestDto;
import com.example.sparta.domain.user.dto.UserPasswordUpdateRequestDto;
import com.example.sparta.domain.user.dto.UserProfileUpdateRequestDto;
import com.example.sparta.domain.user.dto.UserProfileUpdateResponseDto;
import com.example.sparta.domain.user.dto.UserSignupRequestDto;
import com.example.sparta.domain.user.dto.UserSignupResponseDto;
import com.example.sparta.domain.user.entity.User;
import com.example.sparta.domain.user.repository.UserQueryRepository;
import com.example.sparta.domain.user.repository.UserRepository;
import com.example.sparta.global.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  //Repository 주입
  private final UserRepository userRepository;
  private final UserQueryRepository userQueryRepository;
  
  // 비밀번호 인코더 주입
  private final PasswordEncoder passwordEncoder;

  private final JwtUtil jwtUtil;

  @Override
  public UserSignupResponseDto userSignup(UserSignupRequestDto userSignupRequestDto) {
    String email = userSignupRequestDto.getEmail();

    // 이미 가입한 유저인지 체크하기
    if (userQueryRepository.findByEmail(email) != null) {
      // 만약 DB에 동일한 Email 이 존재하면
      throw new IllegalArgumentException("이미 가입된 email 입니다");
    }
    User user = new User(userSignupRequestDto, passwordEncoder);
    // if문 안들어가고 잘 넘어오면 입력받아온 Name, password(인코딩한), email, Address 를 user 에 저장

    userRepository.save(user);
    // 해당 user 를 Repository 를 통해서 DB에 저장.

    // 테스트에서 체크할 내용
    return UserSignupResponseDto.builder()
        .name(user.getName())
        .email(user.getEmail())
        .address(user.getAddress())
        .build();
  }

  @Override
  public void userLogin(UserLoginRequestDto userLoginRequestDto,
      HttpServletResponse httpServletResponse) {
    String email = userLoginRequestDto.getEmail();
    String password = userLoginRequestDto.getPassword();

    User user = userQueryRepository.findByEmail(email);
    if (user == null) {
      throw new IllegalArgumentException("등록된 사용자가 없습니다.");
    }
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    String token = jwtUtil.createToken(user);
    httpServletResponse.setHeader(jwtUtil.AUTHORIZATION_HEADER, token);
  }

  @Override
  public void userLogout(HttpServletResponse httpServletResponse) {
    httpServletResponse.setHeader("Authorization", null);
  }

  @Override
  @Transactional
  public UserProfileUpdateResponseDto userProfileUpdate(
      UserProfileUpdateRequestDto userProfileUpdateRequestDto, User user) {
    String email = user.getEmail();
    User userUp = userQueryRepository.findByEmail(email);
    if (userUp == null) {
      throw new IllegalArgumentException("로그인 유저 정보가 없습니다.");
    }
    userUp.userUpdate(userProfileUpdateRequestDto);
    return UserProfileUpdateResponseDto.builder()
        .name(user.getName())
        .email(user.getEmail())
        .address(user.getAddress())
        .build();
  }

  @Override
  @Transactional
  public void userPasswordUpdate(UserPasswordUpdateRequestDto userPasswordUpdateRequestDto,
      User user) {
    String email = user.getEmail();
    String password = user.getPassword();
    User userUp = userQueryRepository.findByEmail(email);
    if (userUp == null) {
      throw new RuntimeException("로그인 유저 정보가 없습니다.");
    }
    if (userPasswordUpdateRequestDto.getPassword() == null) {
      throw new IllegalArgumentException("현재 비밀번호를 입력해 주세요");
    }
    if (!passwordEncoder.matches(userPasswordUpdateRequestDto.getPassword(),
        password)) {
      throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
    }
    if (userPasswordUpdateRequestDto.getNewPassword() == null) {
      throw new IllegalArgumentException("변경하는 비밀번호를 입력해 주세요");
    }
    if (!userPasswordUpdateRequestDto.getNewPassword()
        .equals(userPasswordUpdateRequestDto.getCheckPassword())) {
      throw new IllegalArgumentException("변경하는 비밀번호가 일치하지 않습니다");
    }

    userUp.userPasswordUpdate(userPasswordUpdateRequestDto, passwordEncoder);
  }
}