package com.yeepay.yop.showcase.shop.modules.order.biz;

import com.yeepay.yop.showcase.shop.modules.order.vo.RefundQueryRequestDTO;
import com.yeepay.yop.showcase.shop.modules.order.vo.RefundQueryResponseDTO;

/**
 * @author jing.ju
 * @description
 * @date 2020/10/12 下午5:28
 */
public interface RefundQueryBizService {

    RefundQueryResponseDTO refundQueryRequest(RefundQueryRequestDTO requestDTO);

}
