package com.zpb.cloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zpb.cloud.dao.PaymentDao;
import com.zpb.cloud.entities.Payment;
import com.zpb.cloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public PageInfo<Payment> getPaymentListPage(int current,int size) {
        PageHelper.startPage(current,size);//从pageNum页开始，每页pageSize条数据
        List<Payment> paymentList = paymentDao.getPaymentListPage();
        return paymentList != null ? new PageInfo<Payment>(paymentList) : null;
    }
}
