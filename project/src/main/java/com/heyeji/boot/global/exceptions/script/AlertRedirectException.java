package com.heyeji.boot.global.exceptions.script;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

@Getter
public class AlertRedirectException extends AlertException{

    private String url;
    private String target;

    public AlertRedirectException(String message, String url, HttpStatus status, String target) {
        super(message,status);

        target = StringUtils.hasText(target) ? target : "self"; //target이 없을땐 self

        this.url = url;
        this.target = target;
    }
    public AlertRedirectException(String message, String url, HttpStatus status) {
        //타켓이 없을때 고정 - self
        this(message, url, status, null);
    }
}
