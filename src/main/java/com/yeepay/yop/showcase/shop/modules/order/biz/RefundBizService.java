package com.yeepay.yop.showcase.shop.modules.order.biz;

import com.yeepay.yop.showcase.shop.modules.order.vo.RefundRequestDTO;
import com.yeepay.yop.showcase.shop.modules.order.vo.RefundResponseDTO;

/**
 * @author jing.ju
 * @description
 * @date 2020/9/29 下午3:52
 */
public interface RefundBizService {

    /**
     * 退款
     *
     * @return
     */
    RefundResponseDTO refundRequest(RefundRequestDTO requestDTO);

}
