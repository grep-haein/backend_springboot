package com.grephi.be.entity.prime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

// lombok 사용
// Security 에서 사용되는 User 에서 파라미터를 추가함.
//@Data         // constructor 도중 에러가 발생하므로 사용하지 않음
@Getter @Setter @ToString
public class UserCustom extends User {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    // 유저의 정보를 더 추가하고 싶다면 이곳과, 아래의 생성자 파라미터를 조절해야 한다.
    private String user_level;

    public UserCustom(String username, String password
//            , boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked
            , Collection authorities
            , String user_level) {
        super(username, password
//                , enabled, accountNonExpired, credentialsNonExpired, accountNonLocked
                , authorities);
        this.user_level = user_level;
    }
}