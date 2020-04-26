package com.zpb.cloud.service;

import com.zpb.cloud.entities.ResultCommont;
import com.zpb.cloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @dec :
 * @Date: 2020/3/31
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@Component
@FeignClient("CLOUND-PAYMENT-SERVICE")
public interface FeignPaymentService {

    @GetMapping("/payment/get/{id}")
    public ResultCommont<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/fegin/timeout")
    public String paymentTimeOut();
}
