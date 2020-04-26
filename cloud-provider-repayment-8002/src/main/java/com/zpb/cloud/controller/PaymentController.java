package com.zpb.cloud.controller;

import com.github.pagehelper.PageInfo;
import com.zpb.cloud.entities.ResultCommont;
import com.zpb.cloud.entities.Payment;
import com.zpb.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @Value("${server.port}")
    private String port;

    @PostMapping("/payment/create")
    public ResultCommont create(@RequestBody Payment payment) { //@RequestBody 防止服务端传不过来数据
        int result = paymentService.create(payment);
        log.info("------------- 插入的结果是：{}", result);
        return result > 0 ? new ResultCommont(200, "插入数据成功" + port, result) : new ResultCommont(444, "插入数据失败");
    }

    @GetMapping("/payment/get/{id}")
    public ResultCommont getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("------------- 查询到的结果是：{}", payment);
        return payment != null ? new ResultCommont(200, "查询数据成功" + port, payment) : new ResultCommont(444, "插入数据失败");
    }

    @GetMapping("/payment/lb")
    public @ResponseBody
    String getPaymentLB() {
        return port;
    }

    @GetMapping("/all/{current}/{size}")
    public ResultCommont<PageInfo> all(@PathVariable("current") int current, @PathVariable("size")int size) {
        PageInfo<Payment> listPayment = paymentService.getPaymentListPage(current,size);
        return listPayment != null ? new ResultCommont(200, "查询数据成功" + port, listPayment) : new ResultCommont(444, "插入数据失败");
    }
}
