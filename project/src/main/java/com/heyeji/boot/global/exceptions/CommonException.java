package com.heyeji.boot.global.exceptions;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Getter @Setter
public class CommonException extends RuntimeException{
    private boolean errorCode; //에러코드 형태이면 직접 메서드 가져와서 사용
    private HttpStatus status;
    private Map<String, List<String>> errorMessages;

    public CommonException(String message){
        this(message,HttpStatus.INTERNAL_SERVER_ERROR); //기본 응답코드 500으로 설정
    }

    public CommonException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
