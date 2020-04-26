package com.zpb.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.zpb.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @dec :
 * @Date: 2020/3/31
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @GetMapping("payment/hystrix/normal")
    public String nomralRequext(){
        return paymentService.nomralRequext();
    }

    @GetMapping("payment/hystrix/timeout")
    public String timeout(){
        return paymentService.timeout();
    }

    @GetMapping("payment/hystrix/circuit/{id}")
    public String  paymentCircuitBreaker(@PathVariable("id")Long id){
        return paymentService.paymentCircuitBreaker(id);
    }

    @GetMapping("payment/hystrix/current/{id}")
    public String  paymentCurrentLimiting(@PathVariable("id")Long id){
        return paymentService.currentLimiting(id);
    }

    @GetMapping("payment/hystrix/id")
    public String id(@RequestParam("id") Long id ){
        return Thread.currentThread().getName()+" 调用id是："+ id +"\t,流水号是："+ IdUtil.simpleUUID();
    }

    @GetMapping("payment/hystrix/zipkin")
    public String zipkin(){
        return " zipkin 被调用 ："+ IdUtil.simpleUUID();
    }



}
