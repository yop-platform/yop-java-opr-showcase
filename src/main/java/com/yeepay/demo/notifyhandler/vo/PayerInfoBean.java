package com.yeepay.demo.notifyhandler.vo;

import lombok.Data;

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/9/29下午2:51
 */
@Data
public class PayerInfoBean {

    /**
     * 银行卡号
     */
    private String bankCardNo;

    /**
     * 银行编号
     */
    private String bankId;

    /**
     * 卡类型
     */
    private String cardType;

    /**
     * 手机号
     */
    private String mobilePhoneNo;

    /**
     * 用户ID
     */
    private String userID;

    /**
     *
     */
    private String unionID;

}
