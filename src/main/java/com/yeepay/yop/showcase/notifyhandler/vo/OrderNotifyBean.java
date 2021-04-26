package com.yeepay.yop.showcase.notifyhandler.vo;

import lombok.Data;

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/9/29下午2:42
 */
@Data
public class OrderNotifyBean extends NotifyBean {

    /**
     * 渠道请求号
     */
    private String channelOrderId;

    /**
     * 银行订单号
     */
    private String bankOrderId;

    /**
     * 支付成功时间
     */
    private String paySuccessDate;

    /**
     * 支付方式
     */
    private String payWay;

    /**
     * 支付金额
     */
    private String payAmount;

    /**
     *
     */
    private String payerInfo;

    /**
     * token
     */
    private String token;

    /**
     *
     */
    private String fundProcessType;

    /**
     * 商户手续费
     */
    private int merchantFee;

    /**
     * 用户手续费
     */
    private int customerFee;

    /**
     *
     */
    private String bizSystemNo;

}
