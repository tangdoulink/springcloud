package com.zpb.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @dec :
 * @Date: 2020/3/31
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@SpringBootApplication
@EnableHystrix
@EnableFeignClients
public class ConsumerOrderFeginHystrixMain7003 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderFeginHystrixMain7003.class,args);
    }

}
