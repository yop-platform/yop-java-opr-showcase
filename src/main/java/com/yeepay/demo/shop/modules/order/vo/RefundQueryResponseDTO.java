package com.yeepay.demo.shop.modules.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jing.ju
 * @description
 * @date 2020/10/12 下午4:48
 */
@Data
public class RefundQueryResponseDTO extends RefundResponseDTO {
    private String refundSuccessDate;
    private BigDecimal realRefundAmount;
    private String failReason;
}
