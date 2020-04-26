package com.zpb.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @dec :
 * @Date: 2020/3/31
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@SpringBootApplication
@EnableFeignClients
public class ConsumerOrderFeignMain7002 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderFeignMain7002.class,args);
    }

}
