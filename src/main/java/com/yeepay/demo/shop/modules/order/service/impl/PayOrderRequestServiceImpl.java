package com.yeepay.demo.shop.modules.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.yeepay.demo.shop.config.YopOprShowcaseConfig;
import com.yeepay.demo.shop.modules.order.entity.PayOrder;
import com.yeepay.demo.shop.modules.order.enums.PayOrderStatusEnum;
import com.yeepay.demo.shop.modules.order.service.PayOrderRequestService;
import com.yeepay.demo.shop.modules.order.service.PayOrderService;
import com.yeepay.demo.shop.modules.order.vo.PayOrderRequestDTO;
import com.yeepay.demo.shop.modules.product.repository.ProductMapper;
import com.yeepay.demo.yop.sdk.service.cashier.support.CashierSupport;
import com.yeepay.demo.yop.sdk.service.trade.TradeClient;
import com.yeepay.demo.yop.sdk.service.trade.TradeClientBuilder;
import com.yeepay.demo.yop.sdk.service.trade.model.YopCreateOrderV2ResDTO;
import com.yeepay.demo.yop.sdk.service.trade.request.OrderRequest;
import com.yeepay.demo.yop.sdk.service.trade.response.OrderResponse;
import com.yeepay.yop.sdk.config.AppSdkConfigProviderRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.Date;

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/9/27下午2:37
 */
@Slf4j
@Service
public class PayOrderRequestServiceImpl implements PayOrderRequestService {

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private YopOprShowcaseConfig yopOprShowcaseConfig;

    private TradeClient tradeClient = TradeClientBuilder.builder().build();

    /**
     * 1 根据ID获取对应的产品
     * 2 根据产品ID获取每一步请求
     *
     * @return
     */
    @Override
    public String payOrderRequest(PayOrderRequestDTO requestDTO) {
        //1 创建订单
        PayOrder payOrder = createPayOrder(requestDTO);

        //3 调用YOP下单
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setParentMerchantNo(yopOprShowcaseConfig.getParentMerchantNo());
        orderRequest.setMerchantNo(yopOprShowcaseConfig.getMerchantNo());
        orderRequest.setOrderId(requestDTO.getRequestNo());
        orderRequest.setOrderAmount(requestDTO.getAmount().toString());
        orderRequest.setGoodsName(requestDTO.getGoodsName());
        orderRequest.setNotifyUrl(yopOprShowcaseConfig.getPayNotifyUrl());
        orderRequest.setRedirectUrl(yopOprShowcaseConfig.getRedirectUrl());
        OrderResponse orderResponse = tradeClient.order(orderRequest);

        //4 更新token
        updatePayOrder(orderResponse.getResult(), payOrder);

        //5 生成标准收银台接口
        String appKey = yopOprShowcaseConfig.getAppKey();
        PrivateKey isvPrivateKey = AppSdkConfigProviderRegistry.getProvider().getConfig(appKey).getDefaultIsvPrivateKey();
        return CashierSupport.getPayUrl(isvPrivateKey,
                appKey,
                yopOprShowcaseConfig.getMerchantNo(),
                orderResponse.getResult().getToken());
    }

    private void updatePayOrder(YopCreateOrderV2ResDTO yopCreateOrderV2Res, PayOrder payOrder) {
        payOrder = payOrderService.queryPayOrderByOrderId(payOrder.getOrderId());
        payOrder.setToken(yopCreateOrderV2Res.getToken());
        payOrder.setStatus(PayOrderStatusEnum.PROCESS.name());
        payOrder.setUniqueOrderNo(yopCreateOrderV2Res.getUniqueOrderNo());
        payOrderService.update(payOrder);
    }

    private PayOrder createPayOrder(PayOrderRequestDTO requestDTO) {
        PayOrder payOrder = new PayOrder();
        requestDTO.setRequestNo(System.currentTimeMillis() + "");
        payOrder.setCreateTime(new Date());
        payOrder.setLastUpdateTime(new Date());
        payOrder.setMerchantNo(yopOprShowcaseConfig.getMerchantNo());
        payOrder.setNotifyUrl(yopOprShowcaseConfig.getPayNotifyUrl());
        payOrder.setRedirectUrl(yopOprShowcaseConfig.getRedirectUrl());
        payOrder.setGoodsName(requestDTO.getGoodsName());
        payOrder.setOrderId(requestDTO.getRequestNo());
        payOrder.setOrderAmount(requestDTO.getAmount());
        payOrder.setStatus(PayOrderStatusEnum.INIT.name());
        payOrder.setProductOrderId(requestDTO.getProductOrderId());
        log.info("生成支付订单信息{}", JSON.toJSONString(payOrder));
        payOrderService.insert(payOrder);
        return payOrder;
    }
}
