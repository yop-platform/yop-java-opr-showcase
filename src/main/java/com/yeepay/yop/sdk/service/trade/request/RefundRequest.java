/*
 * 标准交易
 * <p>名称（中文）：新交易下单接口<br />名称（英文，xx.war）：opr-hessian.war<br />简介：合单支付下单与标准收款内部下单接口合并<br />wiki文档地址：http://wiki.yeepay.com/pages/viewpage.action?pageId=122095805(4.新api分组相关接口规划)<br />预计项目上线时间：2020年6月9日<br />归属/拟申请的sp编码（可选）：opr<br />期望api分组编码、名称（可选）：trade(标准交易)<br />涉及的接口：申请下单、订单查询、申请退款、退款查询</p>
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.yeepay.yop.sdk.service.trade.request;

import com.yeepay.yop.sdk.model.BaseRequest;
public class RefundRequest extends BaseRequest {
    private static final long serialVersionUID = 1L;

    private String parentMerchantNo;

    private String merchantNo;

    private String orderId;

    private String refundRequestId;

    private String uniqueOrderNo;

    private String refundAmount;

    private String description;

    private String memo;

    private String refundAccountType;

    private String notifyUrl;


    /**
     * Get parentMerchantNo
     * @return parentMerchantNo
     **/
    
    public String getParentMerchantNo() {
        return parentMerchantNo;
    }

    public void setParentMerchantNo(String parentMerchantNo) {
        this.parentMerchantNo = parentMerchantNo;
    }

    /**
     * Get merchantNo
     * @return merchantNo
     **/
    
    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    /**
     * Get orderId
     * @return orderId
     **/
    
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * Get refundRequestId
     * @return refundRequestId
     **/
    
    public String getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(String refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    /**
     * Get uniqueOrderNo
     * @return uniqueOrderNo
     **/
    
    public String getUniqueOrderNo() {
        return uniqueOrderNo;
    }

    public void setUniqueOrderNo(String uniqueOrderNo) {
        this.uniqueOrderNo = uniqueOrderNo;
    }

    /**
     * Get refundAmount
     * @return refundAmount
     **/
    
    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * Get description
     * @return description
     **/
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get memo
     * @return memo
     **/
    
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * Get refundAccountType
     * @return refundAccountType
     **/
    
    public String getRefundAccountType() {
        return refundAccountType;
    }

    public void setRefundAccountType(String refundAccountType) {
        this.refundAccountType = refundAccountType;
    }

    /**
     * Get notifyUrl
     * @return notifyUrl
     **/
    
    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @Override
    public String getOperationId() {
        return "refund";
    }
}
