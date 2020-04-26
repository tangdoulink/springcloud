package com.zpb.cloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @dec :
 * @Date: 2020/3/31
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@Service
public class PaymentService {

    public String nomralRequext(){
        return "线程池："+Thread.currentThread().getName()+" normal request " ;
    }

    @HystrixCommand(fallbackMethod = "timeoutErr",commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value ="3000")})
    public String timeout(){
        int timeout = 5;
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+" timeout request 耗时："+timeout;
    }

    public String timeoutErr(){
        return "线程池："+Thread.currentThread().getName()+" 系统繁忙，请稍后重试 timeoutErr request";
    }

    //服务熔断 =============//在时间窗口器(sleepWindowInMilliseconds)内调用熔断方法,假如在requestVolumeThreshold次请求中有errorThresholdPercentage次
    //    //都发生了失败,那么这个断路器就要打开了
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFullBack",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Long id){

        if(id < 0){
            throw new IllegalArgumentException("id is not null or - ");
        }
        String serialNumber = IdUtil.simpleUUID();
        return  Thread.currentThread().getName()+"\t 调用成功,流水号是："+serialNumber;
    }

    public String paymentCircuitBreakerFullBack(@PathVariable("id") Long id){
        return "id 不能为负数:"+id;
    }

    //限流
    @HystrixCommand(fallbackMethod = "currentLimiting_err",//指定回调方法
            //超时时间
            commandProperties = { @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")},
            //并发线程数,默认10
            threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "10")}

            )
    public String currentLimiting(Long id){
        System.out.println("ok id = " + id);
        return Thread.currentThread().getName()+" 调用id是："+ id +"\t 调用成功,流水号是："+IdUtil.simpleUUID();
    }

    public String  currentLimiting_err(Long id){
        System.err.println("err id = " + id);
        return Thread.currentThread().getName()+" 调用id是："+ id +"\t 调用失败,流水号是："+(-1);
    }
}
