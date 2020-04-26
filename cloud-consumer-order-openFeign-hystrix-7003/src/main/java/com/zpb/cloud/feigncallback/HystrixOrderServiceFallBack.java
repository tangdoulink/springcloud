package com.zpb.cloud.feigncallback;

import com.zpb.cloud.service.HystrixOrderService;
import org.springframework.stereotype.Component;

/**
 * @dec :
 * @Date: 2020/3/31
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@Component
public class HystrixOrderServiceFallBack implements HystrixOrderService{
    @Override
    public String nomralRequext() {
        return "HystrixOrderService full back method nomral requext ";
    }

    @Override
    public String timeout() {
        return "HystrixOrderService full back method timeout requext ";
    }

    @Override
    public String zipkin() {
        return "HystrixOrderService full back method zipkin requext ";
    }
}
