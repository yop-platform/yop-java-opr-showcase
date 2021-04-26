package com.yeepay.demo.shop.modules.order.service;

import com.yeepay.demo.shop.modules.order.entity.PayOrder;

import java.util.List;

/**
 * @author jing.ju
 * @description
 * @date 2020/9/25 上午11:10
 */
public interface PayOrderService {

    void insert(PayOrder payOrder);

    PayOrder queryPayOrderByOrderId(String orderId);

    List<PayOrder> queryAllPayOrder();

    void update(PayOrder payOrder);

    List<PayOrder> queryPayOrderByProductOrderId(String productOrderId, String orderId);

    int updataStatus(String status, String orderId);

    PayOrder queryByYbUniqueOrderNo(String uniqueOrderNo);

    int updataOrderToSuccess(PayOrder refundOrder);

    int updataOrderToTimeOut(PayOrder refundOrder);
}
