package com.grephi.be.web;

import com.grephi.be.entity.prime.AdminUserEntity;
import com.grephi.be.entity.prime.AdminUserRepository;
import com.grephi.be.entity.prime.UserCustom;
import com.grephi.be.services.CustomUserDetailService;
import com.grephi.be.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/user")
public class AdminUserController {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailService adminUserService;
    private final AuthenticationManager authenticationManager;
    private final AdminUserRepository adminUserRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> paramMap) {
        String userId = paramMap.get("user_id");
        String userPw = paramMap.get("user_pw");

        UserCustom loginUser = (UserCustom) adminUserService.loadUserByUsername(userId); //userId로 정보 가져오기

        Authentication authentication = authenticationManager.authenticate(     //가져온 정보와 입력한 비밀번호로 검증
                new UsernamePasswordAuthenticationToken(loginUser, userPw)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);   // 검증 통과 후 authentication 세팅

        String accessToken = jwtUtil.createToken(loginUser.getUsername(), loginUser.getUsername(), loginUser.getUser_level());     //accessToken 생성

        Map<String, Object> result = new HashMap<>();
        result.put("user_id", loginUser.getUsername());
        result.put("user_token", accessToken);
        result.put("user_role", loginUser.getAuthorities().stream().findFirst().get().getAuthority());
        result.put("user_level",loginUser.getUser_level());

        if(result != null){
            AdminUserEntity entity = adminUserRepository.findByAdminId(loginUser.getUsername()).orElseThrow(() -> new RuntimeException("사용자를 찾지 못했습니다."));
            entity.setLastLoginAt(LocalDateTime.now());   //마지막 로그인 시간 기록
            adminUserRepository.save(entity);
        }

        return ResponseEntity.ok(result);
    }
}