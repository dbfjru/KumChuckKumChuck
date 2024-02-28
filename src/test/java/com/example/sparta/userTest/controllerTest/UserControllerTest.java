package com.example.sparta.userTest.controllerTest;

import com.example.sparta.domain.user.controller.UserController;
import com.example.sparta.domain.user.dto.UserLoginRequestDto;
import com.example.sparta.domain.user.dto.UserProfileUpdateRequestDto;
import com.example.sparta.domain.user.dto.UserSignupRequestDto;
import com.example.sparta.domain.user.service.UserService;
import com.example.sparta.global.dto.ResponseDto;
import com.example.sparta.global.impl.UserDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    @Mock
    UserDetailsImpl userDetails;
    @Mock
    HttpServletResponse httpServletResponse;
    MockMvc mockMvc;

    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("유저 회원 가입 성공 테스트")
    public void userSignupTest01() {
        //given
        String name = "이름";
        String password = "123456789";
        String email = "test@test.com";
        String address = "테스트 주소";

        UserSignupRequestDto userSignupRequestDto = new UserSignupRequestDto(
            name, password, email, address
        );

        //when

        ResponseEntity<ResponseDto<Void>> response = userController.usersSignup(
            userSignupRequestDto);
        //then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());
    }

    @Test
    @DisplayName("유저 로그인 테스트")
    public void userSignupTest02() {
        //given

        String password = "123456789";
        String email = "test@test.com";

        UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto(
            email, password
        );
        //when

        ResponseEntity<ResponseDto<Void>> response = userController.userLogin(userLoginRequestDto,
            httpServletResponse);
        //then
        System.out.println(response.getStatusCode());
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    @DisplayName("유저 로그아웃 테스트")
    public void userSignupTest03() {
        //given
        //when

        ResponseEntity<ResponseDto<Void>> response = userController.userLogout(httpServletResponse);
        //then
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    @DisplayName("유저 정보수정 테스트")
    public void userSignupTest04() {
        //given
        String name = "변경 유저 이름";
        String address = "변경 주소";
        UserProfileUpdateRequestDto userProfileUpdateRequestDto = new UserProfileUpdateRequestDto(
            name, address
        );

        //when

        ResponseEntity<ResponseDto<Void>> response = userController.userProfileUpdate(
            userProfileUpdateRequestDto, userDetails);
        //then
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    @DisplayName("유저 비밀번호 수정 테스트")
    public void userSignupTest05() {
        //given
        String name = "변경 유저 이름";
        String address = "변경 주소";
        UserProfileUpdateRequestDto userProfileUpdateRequestDto = new UserProfileUpdateRequestDto(
            name, address
        );

        //when

        ResponseEntity<ResponseDto<Void>> response = userController.userProfileUpdate(
            userProfileUpdateRequestDto, userDetails);
        //then
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }


}
