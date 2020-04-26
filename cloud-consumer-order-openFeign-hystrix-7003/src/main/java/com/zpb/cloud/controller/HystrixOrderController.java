package com.zpb.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zpb.cloud.service.HystrixOrderService;
import javafx.beans.DefaultProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @dec :
 * @Date: 2020/3/31
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@RestController
@DefaultProperties(defaultFallback = "globalFallBack")
public class HystrixOrderController {

    @Autowired
    private HystrixOrderService hystrixOrderService;

    @GetMapping("consumer/payment/hystrix/normal")
    public String nomralRequext(){
        return hystrixOrderService.nomralRequext();
    }

    @GetMapping("consumer/payment/hystrix/timeout")
    //定义方法执行3s内正常,其它时间超时

//    @HystrixCommand //如果没有用指明的兜底方法,那么就用默认的,如果有，就用指定的兜底方法,这里采用全局配置
    @HystrixCommand(fallbackMethod = "timeoutErr",commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value ="3000")})
    public String timeout(){
        return hystrixOrderService.timeout();
    }

    public String timeoutErr(){
        return "线程池："+Thread.currentThread().getName()+" 系统繁忙，请稍后重试 timeoutErr request";
    }

    private String globalFallBack(){
        return "I'm global fall back.";
    }

    @GetMapping("consumer/payment/zipkin")
    public String zipkin(){
        return hystrixOrderService.zipkin();
    }
}
