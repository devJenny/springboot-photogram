package com.example.photogram.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer { // web 설정 파일

    @Value("${file.path}")
    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry
                .addResourceHandler("/upload/**") // html 페이지에서 /upload/** 이런 주소 패턴이 나오면 작동
                .addResourceLocations("file:///" + uploadFolder)
                .setCachePeriod(60 * 10 * 6) // 1시간 동안 캐싱
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
