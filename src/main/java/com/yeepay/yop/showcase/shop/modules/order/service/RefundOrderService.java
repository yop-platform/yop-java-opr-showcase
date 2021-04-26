package com.yeepay.yop.showcase.shop.modules.order.service;

import com.yeepay.yop.showcase.shop.modules.order.entity.RefundOrder;

import java.util.List;

/**
 * @author jing.ju
 * @description
 * @date 2020/9/27 下午4:21
 */
public interface RefundOrderService {

    void insert(RefundOrder refundOrder);

    RefundOrder queryRefundOrderByRequestId(String orderId);

    List<RefundOrder> queryAllRefundOrder();

    int updataOrderToProcssing(RefundOrder refundOrder);

    int updataOrderToSuccess(RefundOrder refundOrder);

    int updataOrderToFail(RefundOrder refundOrder);
}
