package com.zpb.cloud.controller;

import com.zpb.cloud.entities.ResultCommont;
import com.zpb.cloud.entities.Payment;
import com.zpb.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @dec :
 * @Date: 2020/3/30
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String port;

    @PostMapping("/payment/create")
    public ResultCommont create(@RequestBody Payment payment){ //@RequestBody 防止服务端传不过来数据
        int result = paymentService.create(payment);
        log.info("------------- 插入的结果是：{}",result);
        return result > 0 ? new ResultCommont(200,"插入数据成功"+port,result):new ResultCommont(444,"插入数据失败");
    }

    @GetMapping("/payment/get/{id}")
    public ResultCommont getPaymentById(@PathVariable("id")Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("------------- 查询到的结果是：{}",payment);
        return payment != null ? new ResultCommont(200,"查询数据成功"+port,payment):new ResultCommont(444,"插入数据失败");
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();//获取所有的注册服务
        for (String service:services ) {
            System.err.println("service = " + service);
        }
        //获取具体服务下的实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUND-PAYMENT-SERVICE");
        for ( ServiceInstance serviceInstance:instances ) {
            System.err.println(serviceInstance.getHost()+"\t" +serviceInstance.getPort());
        }
        return discoveryClient;
    }

    @GetMapping("/payment/lb")
    public @ResponseBody String getPaymentLB(){
        return port;
    }

    @GetMapping("/payment/fegin/timeout")
    public String timeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port;
    }
}
