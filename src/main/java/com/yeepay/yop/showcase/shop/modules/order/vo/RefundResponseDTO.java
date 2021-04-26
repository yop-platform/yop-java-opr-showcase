package com.yeepay.yop.showcase.shop.modules.order.vo;

import lombok.Data;

/**
 * @author jing.ju
 * @description
 * @date 2020/9/29 下午6:03
 */
@Data
public class RefundResponseDTO extends ResponseResult {

    private String uniqueRefundNo;

    private String status;
}
