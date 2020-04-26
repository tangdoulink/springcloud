package com.zpb.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @dec :
 * @Date: 2020/3/31
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        return new RandomRule(); //定义为随机
    }
}
