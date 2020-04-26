package com.zpb.cloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @dec :
 * @Date: 2020/3/31
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@Configuration
public class FeginConfig {

    @Bean
    Logger.Level feginLoggerLevel(){
        return Logger.Level.FULL;
    }
}
