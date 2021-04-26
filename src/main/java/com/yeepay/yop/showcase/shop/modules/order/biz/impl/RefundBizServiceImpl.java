package com.yeepay.yop.showcase.shop.modules.order.biz.impl;

import com.yeepay.yop.showcase.shop.modules.order.biz.RefundBizService;
import com.yeepay.yop.showcase.shop.modules.order.entity.PayOrder;
import com.yeepay.yop.showcase.shop.modules.order.entity.RefundOrder;
import com.yeepay.yop.showcase.shop.modules.order.service.PayOrderService;
import com.yeepay.yop.showcase.shop.modules.order.service.RefundOrderService;
import com.yeepay.yop.showcase.shop.config.YopOprShowcaseConfig;
import com.yeepay.yop.showcase.shop.modules.order.enums.RefundStatus;
import com.yeepay.yop.showcase.shop.modules.order.enums.YopRefundStatus;
import com.yeepay.yop.showcase.shop.modules.order.vo.RefundRequestDTO;
import com.yeepay.yop.showcase.shop.modules.order.vo.RefundResponseDTO;
import com.yeepay.yop.showcase.shop.support.Constant;
import com.yeepay.yop.sdk.service.trade.TradeClient;
import com.yeepay.yop.sdk.service.trade.TradeClientBuilder;
import com.yeepay.yop.sdk.service.trade.model.ResponseRefundDTO;
import com.yeepay.yop.sdk.service.trade.request.RefundRequest;
import com.yeepay.yop.sdk.service.trade.response.RefundResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author jing.ju
 * @description 退款
 * @date 2020/9/29 下午3:12
 */
@Slf4j
@Service
public class RefundBizServiceImpl implements RefundBizService {

    @Autowired
    private RefundOrderService refundOrderService;

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private YopOprShowcaseConfig yopOprShowcaseConfig;

    private TradeClient tradeClient = TradeClientBuilder.builder().build();

    @Override
    public RefundResponseDTO refundRequest(RefundRequestDTO requestDTO) {
        RefundResponseDTO responseDTO = new RefundResponseDTO();
        try {
            // 先校验本地订单状态、退款记录等
            PayOrder origPayOrder = baseValidate(requestDTO);

            RefundOrder refundOrder = createRefundOrder(requestDTO, origPayOrder);

            // 发起YOP退款
            RefundRequest refundRequest = new RefundRequest();
            refundRequest.setParentMerchantNo(yopOprShowcaseConfig.getParentMerchantNo());
            refundRequest.setMerchantNo(yopOprShowcaseConfig.getMerchantNo());
            refundRequest.setNotifyUrl(yopOprShowcaseConfig.getPayNotifyUrl());
            refundRequest.setOrderId(requestDTO.getPayOrderId());
            refundRequest.setRefundRequestId(requestDTO.getRequestNo());
            refundRequest.setUniqueOrderNo(requestDTO.getPayUniqueOrderNo());
            refundRequest.setRefundAmount(requestDTO.getAmount().toString());
            RefundResponse refundResponse = tradeClient.refund(refundRequest);

            dealOrderAfterResponse(refundOrder, refundResponse.getResult());
        } catch (Exception e) {
            log.error("refundRequest exception, detail:{}", e.getMessage());
            responseDTO.setCode(Constant.REFUND_ERROR);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    private RefundOrder createRefundOrder(RefundRequestDTO requestDTO, PayOrder origPayOrder) throws Exception {
        try {
            RefundOrder refundOrder = new RefundOrder();
            requestDTO.setAmount(origPayOrder.getOrderAmount());
            refundOrder.setStatus(RefundStatus.ACCEPT.getCode());
            refundOrder.setRefundRequestId(requestDTO.getRequestNo());
            refundOrder.setCreateTime(new Date());
            refundOrder.setMerchantNo(origPayOrder.getMerchantNo());
            refundOrder.setPayOrderId(origPayOrder.getOrderId());
            refundOrder.setPayUniqueOrderNo(origPayOrder.getUniqueOrderNo());
            refundOrder.setRefundAmount(origPayOrder.getOrderAmount());
            refundOrder.setNotifyUrl(origPayOrder.getNotifyUrl());
            refundOrder.setLastUpdateTime(new Date());
            refundOrderService.insert(refundOrder);
            return refundOrder;
        } catch (Exception e) {
            log.error("insertRefundOrder exception, detail:{}", e.getMessage());
            throw new Exception("insertRefundOrder exception, detail:" + e.getMessage());
        }
    }

    private PayOrder baseValidate(RefundRequestDTO requestDTO) throws Exception {
        PayOrder dbPayOrder = payOrderService.queryByYbUniqueOrderNo(requestDTO.getPayUniqueOrderNo());
        if (null == dbPayOrder) {
            throw new Exception("PayOrder is not exist!");
        }
//        if (!"SUCCESS".equals(dbPayOrder.getStatus())){
//            throw new Exception("PayOrder status is not SUCCESS, can not refund!");
//        }

        return dbPayOrder;
    }

    private void dealOrderAfterResponse(RefundOrder refundOrder, ResponseRefundDTO refundResponse) {
        if (Constant.YOP_TRADE_ORDER_SUCCESS_TYPE.equals(refundResponse.getCode())) {
            refundOrder.setUniqueRefundNo(refundResponse.getUniqueRefundNo());
            if (YopRefundStatus.SUCCESS.name().equals(refundResponse.getStatus())) {
                refundOrder.setRefundSuccessDate(new Date());
                refundOrder.setRealRefundAmount(refundOrder.getRefundAmount());
                refundOrderService.updataOrderToSuccess(refundOrder);
            } else if (YopRefundStatus.PROCESSING.name().equals(refundResponse.getStatus())) {
                refundOrderService.updataOrderToProcssing(refundOrder);
            } else {
                refundOrder.setErrorCode(refundResponse.getCode());
                refundOrder.setErrorMsg(refundResponse.getMessage());
                refundOrderService.updataOrderToFail(refundOrder);
            }
        } else {
            refundOrder.setErrorCode(refundResponse.getCode());
            refundOrder.setErrorMsg(refundResponse.getMessage());
            refundOrderService.updataOrderToFail(refundOrder);
        }
    }
}
