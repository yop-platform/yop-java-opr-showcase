package com.yeepay.yop.showcase.shop.modules.order.vo;

import lombok.Data;

/**
 * @author jing.ju
 * @description
 * @date 2020/9/29 下午3:38
 */
@Data
public class RefundRequestDTO extends YopRequestDTO {
    private String payOrderId;
    private String payUniqueOrderNo;
}
