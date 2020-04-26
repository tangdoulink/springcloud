package com.zpb.cloud.service;

import com.github.pagehelper.PageInfo;
import com.zpb.cloud.entities.Payment;

import java.util.List;

/**
 * @dec :
 * @Date: 2020/3/30
 * @Auther: pengbo.zhao
 * @version: 1.0
 */

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);

    public PageInfo<Payment> getPaymentListPage(int current,int size);



}
