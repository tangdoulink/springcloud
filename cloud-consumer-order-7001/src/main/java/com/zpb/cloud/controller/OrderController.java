package com.zpb.cloud.controller;

import com.zpb.cloud.entities.ResultCommont;
import com.zpb.cloud.entities.Payment;
import com.zpb.cloud.lb.LoadBlancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @dec :
 * @Date: 2020/3/30
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@RestController
@Slf4j
public class OrderController {

//    public static final String PARAMETER_URL = "http://localhost:8001";
    public static final String PARAMETER_URL = "http://CLOUND-PAYMENT-SERVICE";    //客户端要从服务名来获取具体服务,不能通过实例名

    @Autowired
    private LoadBlancer loadBlancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    @Qualifier(value ="defaultRestTemplate")
    private RestTemplate restTemplate; //这个是用到自定义轮询方式,在config里面已经注入过LoadBlancer，所以这里要用一下别名

    @GetMapping("/consumer/payment/create")
    public ResultCommont<Payment> creat(Payment payment){
        return restTemplate.postForObject(PARAMETER_URL+"/payment/create",payment,ResultCommont.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public ResultCommont<Payment> get(@PathVariable("id") Long id){
        return restTemplate.getForObject(PARAMETER_URL+"/payment/get/"+id,ResultCommont.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public ResultCommont<Payment> getEntity(@PathVariable("id") Long id){
        ResponseEntity<ResultCommont> entity = restTemplate.getForEntity(PARAMETER_URL + "/payment/get/" + id, ResultCommont.class);

        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new ResultCommont<Payment>(444,"操作失败");
        }
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUND-PAYMENT-SERVICE");
        if(instances == null || instances.size() == 0){
            return null;
        }
        ServiceInstance serviceInstance = loadBlancer.instance(instances);
        URI uri = serviceInstance.getUri();
        System.out.println("uri = " + uri);
        System.out.println("uri = " + uri+"/payment/lb");
        return restTemplate.getForObject(uri+"/payment/lb", String.class);
    }
}
