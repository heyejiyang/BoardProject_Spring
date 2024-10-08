package com.heyeji.boot.global.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 기본 설정
 */
@Configuration
@EnableJpaAuditing
public class MvcConfig implements WebMvcConfigurer {

    /**
     * <input type="hidden" name="_method" value="PATCH"> -> PATCH 방식으로 요청
     * ?_method=DELETE
     * @return
     */
    @Bean
    public HiddenHttpMethodFilter hiddenMethodFilter(){
        return new HiddenHttpMethodFilter();
    }
}
