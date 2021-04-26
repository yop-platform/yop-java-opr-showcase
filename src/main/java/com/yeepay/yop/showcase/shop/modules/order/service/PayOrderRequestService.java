package com.yeepay.yop.showcase.shop.modules.order.service;

import com.yeepay.yop.showcase.shop.modules.order.vo.PayOrderRequestDTO;

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/9/27下午2:37
 */
public interface PayOrderRequestService {

    /**
     * YOP支付接口
     *
     * @return
     */
    String payOrderRequest(PayOrderRequestDTO requestDTO);
}
