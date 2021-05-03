package com.project5.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project5.common.Constants;



/**
 * MVC 설정 클래스
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	// 정적 resource
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("file:src/main/resources/templates/", "file:src/main/resources/static/") // 정적 데이터 접근
                .addResourceLocations("file:///"+ Constants.getUploadImagesPath() + File.separator); // 물리적 파일 접근
    }
	
}