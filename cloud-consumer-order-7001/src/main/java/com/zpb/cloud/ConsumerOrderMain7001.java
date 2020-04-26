package com.zpb.cloud;

import com.zpb.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @dec :
 * @Date: 2020/3/30
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "CLOUND-PAYMENT-SERVICE",configuration = MySelfRule.class) 自定义轮询方法时,需要将 RestTemplate 上加@LoadBalanced注解
//@RibbonClient(name = "CLOUND-PAYMENT-SERVICE")
public class ConsumerOrderMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderMain7001.class,args);
    }

}
