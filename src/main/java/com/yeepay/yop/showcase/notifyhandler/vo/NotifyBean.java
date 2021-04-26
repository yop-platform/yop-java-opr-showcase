package com.yeepay.yop.showcase.notifyhandler.vo;

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/9/29下午3:08
 */
public class NotifyBean {

    /**
     * 响应码
     */
    private String code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 发起方商编
     */
    private String parentMerchantNo;
    /**
     * 商户编号
     */
    private String merchantNo;
    /**
     * 订单状态
     */
    private String status;
    /**
     * 商户请求号
     */
    private String orderId;
    /**
     * 易宝收款订单号
     */
    private String uniqueOrderNo;
    /**
     * 订单金额
     */
    private String orderAmount;


    public String getParentMerchantNo() {
        return parentMerchantNo;
    }

    public void setParentMerchantNo(String parentMerchantNo) {
        this.parentMerchantNo = parentMerchantNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUniqueOrderNo() {
        return uniqueOrderNo;
    }

    public void setUniqueOrderNo(String uniqueOrderNo) {
        this.uniqueOrderNo = uniqueOrderNo;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
