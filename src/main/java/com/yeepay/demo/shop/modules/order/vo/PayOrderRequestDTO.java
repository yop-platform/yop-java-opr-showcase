package com.yeepay.demo.shop.modules.order.vo;/**
 * Created by yp-tc-m-4855 on 2020/9/27.
 */

import lombok.Data;

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/9/27下午2:50
 */
@Data
public class PayOrderRequestDTO extends YopRequestDTO {

    /**
     * 解决方案ID
     */
    private Integer industrySolutionId;

    /**
     * 订单ID
     */
    private String productOrderId;

}
