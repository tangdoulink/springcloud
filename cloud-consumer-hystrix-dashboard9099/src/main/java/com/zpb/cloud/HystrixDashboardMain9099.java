package com.zpb.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @dec :
 * @Date: 2020/4/5
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardMain9099 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9099.class,args);
    }

}
