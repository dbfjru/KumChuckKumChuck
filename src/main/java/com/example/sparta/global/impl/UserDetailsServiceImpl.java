package com.example.sparta.global.impl;

import com.example.sparta.domain.user.entity.User;
import com.example.sparta.domain.user.entity.UserRoleEnum;
import com.example.sparta.domain.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    // 이메일 값으로 유저 데이터 베이스에서 해당 값을 찾아와서 UserDetails 를 통해 세부 정보를 저장
    public UserDetails getUserDetails(Claims info) {
        User user = new User();
        //info 에서 정보를 추출하여 User 생성
        user.setUserId(info.get("userId", Number.class).longValue());
        user.setName(info.get("name",String.class));
        if(info.get("role",String.class).equals("USER")){
            user.setRole(UserRoleEnum.USER);
        }else{
            user.setRole(UserRoleEnum.ADMIN);
        }
        user.setAddress(info.get("address",String.class));
        user.setEmail(info.getSubject());

        return new UserDetailsImpl(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
