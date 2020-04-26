package com.zpb.cloud.service;

import com.zpb.cloud.feigncallback.HystrixOrderServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @dec :
 * @Date: 2020/3/31
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = HystrixOrderServiceFallBack.class)
public interface HystrixOrderService {

    @GetMapping("payment/hystrix/normal")
    public String nomralRequext();

    @GetMapping("payment/hystrix/timeout")
    public String timeout();

    @GetMapping("payment/hystrix/zipkin")
    public String zipkin();
}
