package com.heyeji.boot.member;

import com.heyeji.boot.member.entities.Member;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
public class MemberInfo implements UserDetails {

    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {  //계정이 만료되지 않았는지
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //계정이 잠겨있지 않은지
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //비번이 만료되지 않았는지
        return true;
    }

    @Override
    public boolean isEnabled() { //계정 활성화 여부, 회원 탈퇴 여부 체크 false: 회원 탈퇴(비활성화)
        return true; //활성화된 상태
    }
}
