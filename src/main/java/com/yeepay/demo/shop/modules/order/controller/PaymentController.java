package com.yeepay.demo.shop.modules.order.controller;

import com.yeepay.demo.shop.modules.order.entity.PayOrder;
import com.yeepay.demo.shop.modules.order.service.PayOrderService;
import com.yeepay.demo.notifyhandler.service.NotifyService;
import com.yeepay.demo.shop.config.YopOprShowcaseConfig;
import com.yeepay.demo.shop.modules.order.enums.PayOrderStatusEnum;
import com.yeepay.demo.yop.sdk.service.trade.TradeClient;
import com.yeepay.demo.yop.sdk.service.trade.TradeClientBuilder;
import com.yeepay.demo.yop.sdk.service.trade.request.OrderQueryRequest;
import com.yeepay.demo.yop.sdk.service.trade.response.OrderQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/9/29下午4:07
 */
@Slf4j
@Controller
@RequestMapping("/redirect")
public class PaymentController {

    @Autowired
    private PayOrderService payOrderService;

    @Qualifier("orderNotifyServiceImpl")
    private NotifyService orderNotifyService;

    @Autowired
    private YopOprShowcaseConfig yopOprShowcaseConfig;

    private TradeClient tradeClient = TradeClientBuilder.builder().build();

    /**
     * 1 查询订单
     * 2 调用查询订单接口
     *
     * @param parentMerchantNo
     * @param merchantNo
     * @param orderId
     * @param sign
     * @return
     */
    @RequestMapping("/callback")
    public Object redirectCallback(String parentMerchantNo, String merchantNo,
                                   String orderId, String sign) {
        log.debug("merchantNo为{},parentMerchantNo为{},orderId为{},sign为{}",
                merchantNo, parentMerchantNo, orderId, sign);
        PayOrder payOrder = payOrderService.queryPayOrderByOrderId(orderId);
        if (PayOrderStatusEnum.PROCESS.name().equals(payOrder.getStatus())) {
            // 对接YOP
            OrderQueryRequest orderQueryRequest = new OrderQueryRequest();
            orderQueryRequest.setParentMerchantNo(yopOprShowcaseConfig.getParentMerchantNo());
            orderQueryRequest.setMerchantNo(yopOprShowcaseConfig.getMerchantNo());
            orderQueryRequest.setOrderId(orderId);
            OrderQueryResponse response = tradeClient.orderQuery(orderQueryRequest);
            log.info("orderId:{}, 支付结果:{}", orderId, response.getResult().getStatus());
        }
        return "redirect:/order/pay/success/" + payOrder.getProductOrderId();
    }
}
