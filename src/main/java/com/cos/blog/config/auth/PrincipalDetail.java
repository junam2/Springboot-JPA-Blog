package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고, 완료되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다.
public class PrincipalDetail implements UserDetails {
    private User user; // 콤포지션

    public PrincipalDetail(User user) {
        this.user = user;
    }

    // 권한 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();

        /*
        전통적인 방법
        collectors.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                // ROLE_ + ~ 가 규칙이다.
                return "ROLE_" + user.getRole();
            }
        });
         */

        collectors.add(()->{return "ROLE"+user.getRole();});

        return collectors;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정 만료 여부 리턴
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 락 여부 리턴
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }
}
