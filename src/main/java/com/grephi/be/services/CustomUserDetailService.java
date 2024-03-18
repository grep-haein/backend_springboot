package com.grephi.be.services;

import com.grephi.be.entity.prime.AdminUserEntity;
import com.grephi.be.entity.prime.AdminUserRepository;
import com.grephi.be.entity.prime.UserCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    // DB 를 통해 유저정보를 가져오기 위해 주입된 매퍼
    @Autowired
    AdminUserRepository adminUserRepository;


    // 시큐리티의 내용 외 파라미터를 추가하고 싶을 때, 아래 사용
    //  제약조건: Controller 에서 Auth를 점검할 때, UserCustom 으로 받아야 함.
    //  예) (변경 전) @AuthenticationPrincipal User user => (변경 후) @AuthenticationPrincipal UserCustom user
//    boolean enabled = true;
//    boolean accountNonExpired = true;
//    boolean credentialsNonExpired = true;
//    boolean accountNonLocked = true;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // 로그인 시도하려는 유저정보 조회
//        MemberDTO memberDTO = memberMapper.chkLogin(user_email);
        List<GrantedAuthority> authorities = new ArrayList<>();
        Optional<AdminUserEntity> adminUserEntity = adminUserRepository.findByAdminId(userId);
        // 조회가 되지않는 고객은 에러발생.
        if(!adminUserEntity.isPresent()){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        if (adminUserEntity.get().getAdminId().equals(userId)) {
            if(adminUserEntity.get().getLevel().equals("7")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
        }

        // 조회한 정보를 userCustom에 담는다.
        // 만약 파라미터를 추가해야한다면 UserCustom 을 먼저 수정한다.
        UserCustom userCustom = new UserCustom(adminUserEntity.get().getAdminId()
                , adminUserEntity.get().getPwd()
//                , enabled, accountNonExpired, credentialsNonExpired, accountNonLocked
                , authorities
                , adminUserEntity.get().getLevel()
        );

        return userCustom;
    }

}