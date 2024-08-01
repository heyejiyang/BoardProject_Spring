package com.heyeji.boot.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test1() throws Exception{
        mockMvc.perform(post("/member/join")
                        .with(csrf().asHeader())
                .param("email","user02@test.org"))
                .andDo(print());
    }

    @Test
    //@WithAnonymousUser //비회원으로 테스트
    @WithMockUser //인증받은 회원일경우
    void test2() throws Exception{
        mockMvc.perform(get("/mypage"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "user01@test.org", authorities = "ADMIN") //관리자 권한 부여
    void test3() throws Exception{
        mockMvc.perform(get("/admin"))
                .andDo(print());
    }
}
