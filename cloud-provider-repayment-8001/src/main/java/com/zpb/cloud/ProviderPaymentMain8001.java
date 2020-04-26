package com.zpb.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @dec :
 * @Date: 2020/3/30
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderPaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderPaymentMain8001.class,args);
    }
}
