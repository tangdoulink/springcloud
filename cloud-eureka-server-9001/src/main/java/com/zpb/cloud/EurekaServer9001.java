package com.zpb.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @dec :
 * @Date: 2020/3/30
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer9001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer9001.class,args);
    }
}
