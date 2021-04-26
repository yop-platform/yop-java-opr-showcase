package com.yeepay.yop.showcase.shop.modules.order.service.impl;

import com.yeepay.yop.showcase.shop.modules.order.entity.RefundOrder;
import com.yeepay.yop.showcase.shop.modules.order.repository.RefundOrderDao;
import com.yeepay.yop.showcase.shop.modules.order.service.RefundOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jing.ju
 * @description
 * @date 2020/9/27 下午4:22
 */
@Slf4j
@Service
public class RefundOrderServiceImpl implements RefundOrderService {

    @Resource
    private RefundOrderDao refundOrderDao;

    @Override
    public void insert(RefundOrder refundOrder) {
        try {
            refundOrderDao.insert(refundOrder);
        } catch (Exception e) {
            log.error("insert refundOrder exception, detail:{}", e.getMessage());
        }
    }

    @Override
    public RefundOrder queryRefundOrderByRequestId(String orderId) {
        try {
            return refundOrderDao.queryByRefundRequestId(orderId);
        } catch (Exception e) {
            log.error("queryRefundOrderByRequestId, detail:{}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<RefundOrder> queryAllRefundOrder() {
        try {
            return refundOrderDao.getAll();
        } catch (Exception e) {
            log.error("queryAllRefundOrder exception, detail:{}", e.getMessage());
            return null;
        }
    }

    @Override
    public int updataOrderToProcssing(RefundOrder refundOrder) {
        try {
            return refundOrderDao.updataOrderToProcssing(refundOrder.getUniqueRefundNo(), refundOrder.getRefundRequestId());
        } catch (Exception e) {
            log.error("updataOrderToProcssing exception, detail:{}", e.getMessage());
            return 0;
        }
    }

    @Override
    public int updataOrderToSuccess(RefundOrder refundOrder) {
        try {
            return refundOrderDao.updataOrderToSuccess(refundOrder.getRefundSuccessDate(), refundOrder.getUniqueRefundNo(),
                    refundOrder.getRefundRequestId(), refundOrder.getRealRefundAmount());
        } catch (Exception e) {
            log.error("updataOrderToSuccess exception, detail:{}", e.getMessage());
            return 0;
        }
    }

    @Override
    public int updataOrderToFail(RefundOrder refundOrder) {
        try {
            return refundOrderDao.updataOrderToFail(refundOrder.getErrorCode(), refundOrder.getErrorMsg(),
                    refundOrder.getUniqueRefundNo(), refundOrder.getRefundRequestId());
        } catch (Exception e) {
            log.error("updataOrderToFail exception, detail:{}", e.getMessage());
            return 0;
        }
    }
}
