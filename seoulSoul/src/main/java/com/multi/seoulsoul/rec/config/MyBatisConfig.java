package com.multi.seoulsoul.rec.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan(basePackages = {"com.multi.seoulsoul.rec.model.dao", "com.multi.seoulsoul.event.model.dao"})
public class MyBatisConfig implements WebMvcConfigurer {

	  @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/resources/upload/**")
	                .addResourceLocations("file:src/main/webapp/resources/upload/");
	    }
}