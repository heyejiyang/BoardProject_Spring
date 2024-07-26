package com.heyeji.boot.global.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 파일 설정
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(FileProperties.class)
public class FileConfig implements WebMvcConfigurer {

//    @Value("${file.upload.path}")
//    private String path;
//    @Value("${file.upload.url}")
//    private String url;
    private final FileProperties properties;
    //범주화한 설정을 주입 받아서 사용

    //정적 경로 설정
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /upload/**
        registry.addResourceHandler(properties.getUrl()+"**").addResourceLocations("file:///"+properties.getPath());
    }
}
