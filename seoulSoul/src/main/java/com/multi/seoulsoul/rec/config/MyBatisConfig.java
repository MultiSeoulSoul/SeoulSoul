package com.multi.seoulsoul.rec.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.multi.seoulsoul.rec.model.dao", "com.multi.seoulsoul.event.model.dao"})
public class MyBatisConfig {
    
}