package com.yeepay.demo.shop.modules.order.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jing.ju
 * @description
 * @date 2020/9/24 下午4:24
 */
@Data
public class RefundOrder implements Serializable {
    private static final long serialVersionUID = -8922540385552347939L;

    private Integer id;

    private String merchantNo;

    private String payOrderId;

    private String refundRequestId;

    private String uniqueRefundNo;

    private String payUniqueOrderNo;

    private BigDecimal refundAmount;

    private BigDecimal realRefundAmount;

    private String description;

    private String memo;

    private String refundAccountType;

    private String notifyUrl;

    private String errorCode;

    private String errorMsg;

    private String status;

    private Date refundSuccessDate;

    private Date createTime;

    private Date lastUpdateTime;
}
