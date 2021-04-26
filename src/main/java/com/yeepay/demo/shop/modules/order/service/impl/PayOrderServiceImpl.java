package com.yeepay.demo.shop.modules.order.service.impl;

import com.yeepay.demo.shop.modules.order.entity.PayOrder;
import com.yeepay.demo.shop.modules.order.repository.PayOrderDao;
import com.yeepay.demo.shop.modules.order.service.PayOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jing.ju
 * @description
 * @date 2020/9/25 上午11:14
 */
@Slf4j
@Service
public class PayOrderServiceImpl implements PayOrderService {

    @Resource
    private PayOrderDao payOrderDao;

    @Override
    public void insert(PayOrder payOrder) {
        try {
            payOrderDao.insert(payOrder);
        } catch (Exception e) {
            log.error("insert payOrder exception, detail:{}", e.getMessage());
        }
    }

    @Override
    public PayOrder queryPayOrderByOrderId(String orderId) {
        try {
            return payOrderDao.queryPayOrderByOrderId(orderId);
        } catch (Exception e) {
            log.error("queryPayOrderByOrderId, detail:{}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<PayOrder> queryAllPayOrder() {
        try {
            return payOrderDao.getAll();
        } catch (Exception e) {
            log.error("queryAllPayOrder exception, detail:{}", e.getMessage());
            return null;
        }
    }

    @Override
    public void update(PayOrder payOrder) {
        payOrderDao.update(payOrder);
    }

    @Override
    public List<PayOrder> queryPayOrderByProductOrderId(String productOrderId, String orderId) {
        return payOrderDao.queryPayOrderByProductOrderId(productOrderId, orderId);
    }

    @Override
    public int updataStatus(String status, String orderId) {
        return payOrderDao.updataStatus(status, orderId);
    }

    @Override
    public PayOrder queryByYbUniqueOrderNo(String uniqueOrderNo) {
        try {
            return payOrderDao.queryByYbUniqueOrderNo(uniqueOrderNo);
        } catch (Exception e) {
            log.error("queryByYbUniqueOrderNo exception, detail:{}", e.getMessage());
            return null;
        }
    }

    @Override
    public int updataOrderToSuccess(PayOrder payOrder) {
        try {
            return payOrderDao.updataOrderToSuccess(payOrder.getUniqueOrderNo(), payOrder.getPaySuccessDate(),
                    payOrder.getBankOrderId(), payOrder.getMerchantFee(), payOrder.getCustomerFee(),
                    payOrder.getOrderId());
        } catch (Exception e) {
            log.error("updataOrderToSuccess exception, detail:{}", e.getMessage());
            return 0;
        }
    }

    @Override
    public int updataOrderToTimeOut(PayOrder payOrder) {
        try {
            return payOrderDao.updataOrderToTimeOut(payOrder.getErrorMsg(), payOrder.getUniqueOrderNo(),
                    payOrder.getOrderId());
        } catch (Exception e) {
            log.error("updataOrderToTimeOut exception, detail:{}", e.getMessage());
            return 0;
        }
    }
}
