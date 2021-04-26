package com.yeepay.demo.shop.modules.order.vo;

import lombok.Data;

/**
 * @author jing.ju
 * @description
 * @date 2020/10/12 下午4:45
 */
@Data
public class RefundQueryRequestDTO extends YopRequestDTO {
    private String merchantNo;
    private String payOrderId;
    private String refundRequestId;
    private String uniqueRefundNo;
}
