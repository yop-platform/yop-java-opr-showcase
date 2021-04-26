package com.yeepay.yop.showcase.shop.modules.order.biz.impl;

import com.yeepay.yop.showcase.shop.modules.order.biz.RefundQueryBizService;
import com.yeepay.yop.showcase.shop.modules.order.entity.RefundOrder;
import com.yeepay.yop.showcase.shop.modules.order.service.RefundOrderService;
import com.yeepay.yop.showcase.shop.config.YopOprShowcaseConfig;
import com.yeepay.yop.showcase.shop.modules.order.enums.YopRefundStatus;
import com.yeepay.yop.showcase.shop.modules.order.vo.RefundQueryRequestDTO;
import com.yeepay.yop.showcase.shop.modules.order.vo.RefundQueryResponseDTO;
import com.yeepay.yop.showcase.shop.support.Constant;
import com.yeepay.yop.sdk.service.trade.TradeClient;
import com.yeepay.yop.sdk.service.trade.TradeClientBuilder;
import com.yeepay.yop.sdk.service.trade.model.QueryRefundResponseDTO;
import com.yeepay.yop.sdk.service.trade.request.RefundQueryRequest;
import com.yeepay.yop.sdk.service.trade.response.RefundQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jing.ju
 * @description
 * @date 2020/10/12 下午5:29
 */
@Slf4j
@Service
public class RefundQueryBizServiceImpl implements RefundQueryBizService {

    @Autowired
    private RefundOrderService refundOrderService;

    @Autowired
    private YopOprShowcaseConfig yopOprShowcaseConfig;

    private TradeClient tradeClient = TradeClientBuilder.builder().build();

    @Override
    public RefundQueryResponseDTO refundQueryRequest(RefundQueryRequestDTO requestDTO) {
        RefundQueryResponseDTO responseDTO = new RefundQueryResponseDTO();
        try {
            // 先查询本地订单

            // 如果本地状态没有更新，主动查询YOP
            RefundQueryRequest refundQueryRequest = new RefundQueryRequest();
            refundQueryRequest.setParentMerchantNo(yopOprShowcaseConfig.getParentMerchantNo());
            refundQueryRequest.setMerchantNo(yopOprShowcaseConfig.getMerchantNo());
            refundQueryRequest.setOrderId(requestDTO.getRequestNo());
            RefundQueryResponse refundQueryResponse = tradeClient.refundQuery(refundQueryRequest);

            dealOrderAfterResponse(requestDTO, refundQueryResponse.getResult());
        } catch (Exception e) {
            log.error("refundRequest exception, detail:{}", e.getMessage());
            responseDTO.setCode(Constant.REFUND_QUERY_ERROR);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    private void dealOrderAfterResponse(RefundQueryRequestDTO requestDTO, QueryRefundResponseDTO queryRefundResponse) {
        RefundOrder dbRefundOrder = refundOrderService.queryRefundOrderByRequestId(requestDTO.getRefundRequestId());
        dbRefundOrder.setUniqueRefundNo(queryRefundResponse.getUniqueRefundNo());
        if (YopRefundStatus.SUCCESS.name().equals(queryRefundResponse.getStatus())) {
            Date refundSuccessDate = new Date();
            try {
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                refundSuccessDate = ft.parse(queryRefundResponse.getRefundSuccessDate());
            } catch (Exception e) {
                log.error("parse Date error.");
            }
            dbRefundOrder.setRefundSuccessDate(refundSuccessDate);
            dbRefundOrder.setRealRefundAmount(queryRefundResponse.getRealRefundAmount());
            refundOrderService.updataOrderToSuccess(dbRefundOrder);
        } else if (YopRefundStatus.REJECT.name().equals(queryRefundResponse.getStatus())) {
            dbRefundOrder.setErrorMsg(queryRefundResponse.getFailReason());
            refundOrderService.updataOrderToFail(dbRefundOrder);
        }
    }

}
