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


package com.yeepay.yop.sdk.service.trade.model;

import org.apache.commons.lang3.ObjectUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.yeepay.yop.sdk.service.trade.model.YopSubOrderDetailDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * YopCreateOrderV2ResDTO
 */
public class YopCreateOrderV2ResDTO implements Serializable{

  private static final long serialVersionUID = 1L;

  /**
   * 返回码
   */
  @JsonProperty("code")
  private String code = null;

  /**
   * 返回信息
   */
  @JsonProperty("message")
  private String message = null;

  /**
   * 发起方商编
   */
  @JsonProperty("parentMerchantNo")
  private String parentMerchantNo = null;

  /**
   * 商户编号
   */
  @JsonProperty("merchantNo")
  private String merchantNo = null;

  /**
   * 商户收款请求号
   */
  @JsonProperty("orderId")
  private String orderId = null;

  /**
   * 易宝收款订单号
   */
  @JsonProperty("uniqueOrderNo")
  private String uniqueOrderNo = null;

  /**
   * token
   */
  @JsonProperty("token")
  private String token = null;

  /**
   * 订单金额
   */
  @JsonProperty("orderAmount")
  private BigDecimal orderAmount = null;

  /**
   * 子单域信息
   */
  @JsonProperty("subOrderInfoList")
  private List<YopSubOrderDetailDTO> subOrderInfoList = null;

  public YopCreateOrderV2ResDTO code(String code) {
    this.code = code;
    return this;
  }

   /**
   * 返回码
   * @return code
  **/

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public YopCreateOrderV2ResDTO message(String message) {
    this.message = message;
    return this;
  }

   /**
   * 返回信息
   * @return message
  **/

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public YopCreateOrderV2ResDTO parentMerchantNo(String parentMerchantNo) {
    this.parentMerchantNo = parentMerchantNo;
    return this;
  }

   /**
   * 发起方商编
   * @return parentMerchantNo
  **/

  public String getParentMerchantNo() {
    return parentMerchantNo;
  }

  public void setParentMerchantNo(String parentMerchantNo) {
    this.parentMerchantNo = parentMerchantNo;
  }

  public YopCreateOrderV2ResDTO merchantNo(String merchantNo) {
    this.merchantNo = merchantNo;
    return this;
  }

   /**
   * 商户编号
   * @return merchantNo
  **/

  public String getMerchantNo() {
    return merchantNo;
  }

  public void setMerchantNo(String merchantNo) {
    this.merchantNo = merchantNo;
  }

  public YopCreateOrderV2ResDTO orderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

   /**
   * 商户收款请求号
   * @return orderId
  **/

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public YopCreateOrderV2ResDTO uniqueOrderNo(String uniqueOrderNo) {
    this.uniqueOrderNo = uniqueOrderNo;
    return this;
  }

   /**
   * 易宝收款订单号
   * @return uniqueOrderNo
  **/

  public String getUniqueOrderNo() {
    return uniqueOrderNo;
  }

  public void setUniqueOrderNo(String uniqueOrderNo) {
    this.uniqueOrderNo = uniqueOrderNo;
  }

  public YopCreateOrderV2ResDTO token(String token) {
    this.token = token;
    return this;
  }

   /**
   * token
   * @return token
  **/

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public YopCreateOrderV2ResDTO orderAmount(BigDecimal orderAmount) {
    this.orderAmount = orderAmount;
    return this;
  }

   /**
   * 订单金额
   * @return orderAmount
  **/

  public BigDecimal getOrderAmount() {
    return orderAmount;
  }

  public void setOrderAmount(BigDecimal orderAmount) {
    this.orderAmount = orderAmount;
  }

  public YopCreateOrderV2ResDTO subOrderInfoList(List<YopSubOrderDetailDTO> subOrderInfoList) {
    this.subOrderInfoList = subOrderInfoList;
    return this;
  }

  public YopCreateOrderV2ResDTO addSubOrderInfoListItem(YopSubOrderDetailDTO subOrderInfoListItem) {
    if (this.subOrderInfoList == null) {
      this.subOrderInfoList = new ArrayList<>();
    }
    this.subOrderInfoList.add(subOrderInfoListItem);
    return this;
  }

   /**
   * 子单域信息
   * @return subOrderInfoList
  **/

  public List<YopSubOrderDetailDTO> getSubOrderInfoList() {
    return subOrderInfoList;
  }

  public void setSubOrderInfoList(List<YopSubOrderDetailDTO> subOrderInfoList) {
    this.subOrderInfoList = subOrderInfoList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
  if (this == o) {
    return true;
  }
  if (o == null || getClass() != o.getClass()) {
    return false;
  }
    YopCreateOrderV2ResDTO yopCreateOrderV2ResDTO = (YopCreateOrderV2ResDTO) o;
    return ObjectUtils.equals(this.code, yopCreateOrderV2ResDTO.code) &&
    ObjectUtils.equals(this.message, yopCreateOrderV2ResDTO.message) &&
    ObjectUtils.equals(this.parentMerchantNo, yopCreateOrderV2ResDTO.parentMerchantNo) &&
    ObjectUtils.equals(this.merchantNo, yopCreateOrderV2ResDTO.merchantNo) &&
    ObjectUtils.equals(this.orderId, yopCreateOrderV2ResDTO.orderId) &&
    ObjectUtils.equals(this.uniqueOrderNo, yopCreateOrderV2ResDTO.uniqueOrderNo) &&
    ObjectUtils.equals(this.token, yopCreateOrderV2ResDTO.token) &&
    ObjectUtils.equals(this.orderAmount, yopCreateOrderV2ResDTO.orderAmount) &&
    ObjectUtils.equals(this.subOrderInfoList, yopCreateOrderV2ResDTO.subOrderInfoList);
  }

  @Override
  public int hashCode() {
    return ObjectUtils.hashCodeMulti(code, message, parentMerchantNo, merchantNo, orderId, uniqueOrderNo, token, orderAmount, subOrderInfoList);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class YopCreateOrderV2ResDTO {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    parentMerchantNo: ").append(toIndentedString(parentMerchantNo)).append("\n");
    sb.append("    merchantNo: ").append(toIndentedString(merchantNo)).append("\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    uniqueOrderNo: ").append(toIndentedString(uniqueOrderNo)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    orderAmount: ").append(toIndentedString(orderAmount)).append("\n");
    sb.append("    subOrderInfoList: ").append(toIndentedString(subOrderInfoList)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

