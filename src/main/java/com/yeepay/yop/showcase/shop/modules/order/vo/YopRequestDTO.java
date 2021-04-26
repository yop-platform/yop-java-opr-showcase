package com.yeepay.yop.showcase.shop.modules.order.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/9/25上午10:05
 */
@Data
public class YopRequestDTO implements Serializable {

    /**
     * 请求号
     */
    private String requestNo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 订单金额
     */
    private BigDecimal amount;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     *
     */
    private String token;

}
