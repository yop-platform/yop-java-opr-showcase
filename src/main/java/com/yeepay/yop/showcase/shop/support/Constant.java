package com.yeepay.yop.showcase.shop.support;/**
 * Created by yp-tc-m-4855 on 2020/9/27.
 */

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/9/27下午2:30
 */
public class Constant {

    /**
     * OPR成功
     */
    public static final String YOP_TRADE_ORDER_SUCCESS_TYPE = "OPR00000";

    /**
     * 支付通知地址
     */
    public static final String NOTIFY_PAY_TYPE = "/orderNotifyCallback";

    /**
     * 退款通知地址
     */
    public static final String NOITFY_REFUND_TYPE = "/refundNotifyCallback";

    /**
     * 退款异常
     */
    public static final String REFUND_ERROR = "REFUND000001";

    /**
     * 退款查询异常
     */
    public static final String REFUND_QUERY_ERROR = "REFUND000002";

    /**
     * 支付查询异常
     */
    public static final String PAY_QUERY_ERROR = "PAY000002";
}
