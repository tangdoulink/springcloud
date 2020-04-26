package com.zpb.cloud.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @dec :
 * @Date: 2020/3/30
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@Configuration
public class ApplicationContextConfig {

    @Bean(name="defaultRestTemplate")
    @LoadBalanced //默认是轮询访问  如果自定义算法了，需要将其关掉，如果没有，就采用默认轮询方式
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
