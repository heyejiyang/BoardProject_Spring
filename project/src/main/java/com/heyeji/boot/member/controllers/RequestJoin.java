package com.heyeji.boot.member.controllers;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestJoin { //사용자가 작성한 데이터를 전달하는 커맨드 객체
    @NotBlank @Email //필수항목 검증(Bean Validation API로 1차 검증)
    private String email;
    @NotBlank @Size(min = 8,max = 20) //숫자, 대소문자, 특수문자 포함시키기
    private String password;
    @NotBlank
    private String confirmPassword;
    @NotBlank
    private String userName;
    @NotBlank
    private String mobile;
    @AssertTrue //true가 되야지 통과
    private boolean agree;
}
