package com.yeepay.yop.showcase.shop.modules.order.biz.impl;

import com.yeepay.yop.showcase.shop.modules.order.biz.PayOrderQueryBizService;
import com.yeepay.yop.showcase.shop.modules.order.entity.PayOrder;
import com.yeepay.yop.showcase.shop.modules.order.service.PayOrderService;
import com.yeepay.yop.showcase.notifyhandler.vo.OrderNotifyBean;
import com.yeepay.yop.showcase.shop.config.YopOprShowcaseConfig;
import com.yeepay.yop.showcase.shop.modules.order.enums.YopPayStatus;
import com.yeepay.yop.showcase.shop.modules.order.vo.PayOrderQueryRequestDTO;
import com.yeepay.yop.showcase.shop.support.Constant;
import com.yeepay.yop.sdk.service.trade.TradeClient;
import com.yeepay.yop.sdk.service.trade.TradeClientBuilder;
import com.yeepay.yop.sdk.service.trade.model.YopQueryOrderResDTO;
import com.yeepay.yop.sdk.service.trade.request.OrderQueryRequest;
import com.yeepay.yop.sdk.service.trade.response.OrderQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jing.ju
 * @description
 * @date 2020/10/13 下午2:14
 */
@Slf4j
@Service
public class PayOrderQueryBizServiceImpl implements PayOrderQueryBizService {

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private YopOprShowcaseConfig yopOprShowcaseConfig;

    private TradeClient tradeClient = TradeClientBuilder.builder().build();

    @Override
    public OrderNotifyBean payOrderQueryRequest(PayOrderQueryRequestDTO requestDTO) {
        OrderNotifyBean responseDTO = new OrderNotifyBean();
        try {
            // 对接YOP
            OrderQueryRequest orderQueryRequest = new OrderQueryRequest();
            orderQueryRequest.setParentMerchantNo(yopOprShowcaseConfig.getParentMerchantNo());
            orderQueryRequest.setMerchantNo(yopOprShowcaseConfig.getMerchantNo());
            orderQueryRequest.setOrderId(requestDTO.getRequestNo());
            OrderQueryResponse response = tradeClient.orderQuery(orderQueryRequest);

            dealOrderAfterResponse(requestDTO, response.getResult());

            BeanUtils.copyProperties(response.getResult(), responseDTO);
        } catch (Exception e) {
            log.error("refundRequest exception, detail:{}", e.getMessage());
            responseDTO.setCode(Constant.PAY_QUERY_ERROR);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    private void dealOrderAfterResponse(PayOrderQueryRequestDTO requestDTO, YopQueryOrderResDTO response) {
        PayOrder dbPayOrder = payOrderService.queryPayOrderByOrderId(requestDTO.getRequestNo());
        dbPayOrder.setUniqueOrderNo(response.getUniqueOrderNo());
        if (YopPayStatus.SUCCESS.name().equals(response.getStatus())) {
            Date paySuccessDate = new Date();
            try {
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                paySuccessDate = ft.parse(response.getPaySuccessDate());
            } catch (Exception e) {
                log.error("parse Date error.");
            }
            dbPayOrder.setPaySuccessDate(paySuccessDate);
            dbPayOrder.setBankOrderId(response.getBankOrderId());
            dbPayOrder.setMerchantFee(response.getMerchantFee());
            dbPayOrder.setCustomerFee(response.getCustomerFee());
            payOrderService.updataOrderToSuccess(dbPayOrder);
        } else if (YopPayStatus.TIME_OUT.name().equals(response.getStatus())) {
            dbPayOrder.setErrorMsg("订单超时");
            payOrderService.updataOrderToTimeOut(dbPayOrder);
        }
    }

}
