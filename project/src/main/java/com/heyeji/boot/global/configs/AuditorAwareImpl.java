package com.heyeji.boot.global.configs;

import com.heyeji.boot.member.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> { //db에 들어갈 데이터

    private final MemberUtil memberUtil;

    @Override
    public Optional<String> getCurrentAuditor() {
        //로그인시 회원정보에서 이메일 가져오고 로그인 아닐시 null값
        String email = memberUtil.isLogin() ? memberUtil.getMember().getEmail() : null;

        return Optional.ofNullable(email);
    }
}
