package com.zpb.cloud.service.impl;

import com.zpb.cloud.dao.PaymentDao;
import com.zpb.cloud.entities.Payment;
import com.zpb.cloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @dec :
 * @Date: 2020/3/30
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService{

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
