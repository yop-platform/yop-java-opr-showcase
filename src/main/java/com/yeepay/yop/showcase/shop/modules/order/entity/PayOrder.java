package com.yeepay.yop.showcase.shop.modules.order.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jing.ju
 * @description
 * @date 2020/9/24 下午3:28
 */
@Data
public class PayOrder implements Serializable {

    private static final long serialVersionUID = -1L;

    private Integer id;

    private String merchantNo;

    private String orderId;

    private BigDecimal orderAmount;

    private String goodsName;

    private String fundProcessType;

    private String notifyUrl;

    private String memo;

    private Date expiredTime;

    private String redirectUrl;

    private String errorCode;

    private String errorMsg;

    private String bizSystemNo;

    private String uniqueOrderNo;

    private String token;

    private String status;

    private Date paySuccessDate;

    private String payWay;

    private String channel;

    private String cardType;

    private String bankId;

    private String bankCardNo;

    private String mobilePhoneNo;

    private String userId;

    private String unionId;

    private String productOrderId;

    private Date createTime;

    private Date lastUpdateTime;

    private String bankOrderId;

    private BigDecimal merchantFee;

    private BigDecimal customerFee;

}
