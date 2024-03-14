package com.grephi.be.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final String uploadImagePath;

    public WebMvcConfig(@Value("${custom.path.upload-images}") String uploadImagePath) {
        this.uploadImagePath = uploadImagePath;
    }

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        SortHandlerMethodArgumentResolver sortArgumentResolver = new SortHandlerMethodArgumentResolver();
        sortArgumentResolver.setSortParameter("sortBy");
        sortArgumentResolver.setPropertyDelimiter("-");

        PageableHandlerMethodArgumentResolver pageableArgumentResolver = new PageableHandlerMethodArgumentResolver(sortArgumentResolver);
        pageableArgumentResolver.setOneIndexedParameters(true);
        pageableArgumentResolver.setMaxPageSize(500);
        pageableArgumentResolver.setFallbackPageable(PageRequest.of(0, 10));
        argumentResolvers.add(pageableArgumentResolver);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		addResourceHandler : 스프링부트에서 확인할 폴더 위치 설정 ex) http://localhost:8082/upload/img/111.png
//		addResourceLocations : 실제 시스템의 폴더 위치, 리눅스 시스템의 경우 'file:경로', 윈도우 시스템의 경우 'file:///경로' 형태 ex) 리눅스 : file:/DATA/video/, 윈도우 file:///D:/temp/

        registry.addResourceHandler("/upload/img/**").addResourceLocations("file:" + uploadImagePath);
//        registry.addResourceHandler("/static/img/**").addResourceLocations("file:///" + uploadImagePath);
    }
}