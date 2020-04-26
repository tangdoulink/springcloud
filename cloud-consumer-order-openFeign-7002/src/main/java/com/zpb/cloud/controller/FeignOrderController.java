package com.zpb.cloud.controller;

import com.zpb.cloud.entities.ResultCommont;
import com.zpb.cloud.entities.Payment;
import com.zpb.cloud.service.FeignPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @dec :
 * @Date: 2020/3/31
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@RestController
@Slf4j
public class FeignOrderController {

    @Resource
    private FeignPaymentService feignPaymentService;

    @GetMapping("/consumer/payment/get/{id}")
    public ResultCommont<Payment> getPaymentById(@PathVariable("id")Long id){
        return feignPaymentService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/fegin/timeout")
    public String paymentTimeOut(){
        return feignPaymentService.paymentTimeOut();
    }

}
