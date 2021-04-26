package com.yeepay.yop.showcase.notifyhandler.vo;/**
 * Created by yp-tc-m-4855 on 2020/9/29.
 */

import lombok.Data;

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/9/29下午3:11
 */
@Data
public class RefundNotifyBean extends NotifyBean {

    /**
     * 退款申请金额
     */
    private String refundAmount;

    /**
     * 退款请求号
     */
    private String refundRequestId;

    /**
     * 易宝退款订单号
     */
    private String uniqueRefundNo;

    /**
     * 退款请求时间
     */
    private String refundRequestDate;

    /**
     * 退款失败原因
     */
    private String errorMessage;

}
