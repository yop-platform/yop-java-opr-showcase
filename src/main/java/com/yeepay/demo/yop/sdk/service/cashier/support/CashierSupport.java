/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */

package com.yeepay.demo.yop.sdk.service.cashier.support;

import com.google.common.base.Charsets;
import com.yeepay.yop.sdk.security.DigestAlgEnum;
import com.yeepay.yop.sdk.security.rsa.RSA;
import com.yeepay.yop.sdk.utils.Encodes;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * title: <br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author dreambt
 * @version 1.0.0
 * @since 2020/11/5 15:57
 */
public final class CashierSupport {

    private static final String CASHIER_URL = "https://cash.yeepay.com/cashier/std";

    public static String getPayUrl(PrivateKey privateKey, String appKey, String merchantNo, String token) {
        return getPayUrl(privateKey, appKey, merchantNo, token, CASHIER_URL);
    }

    public static String getPayUrl(PrivateKey privateKey, String appKey, String merchantNo, String token, String cashierUrl) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appKey", appKey);
        //系统商商编
        params.put("merchantNo", merchantNo);
        //token 调创建订单接口获取
        params.put("token", token);
        //时间戳
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        //直联编码
        params.put("directPayType", "");
        //卡类型只适用于银行卡快捷支付
        params.put("cardType", "");
        //用户标识银行卡快捷支付用于记录绑卡
        params.put("userNo", "2372373273278238");
        //用户标识类型
        params.put("userType", "USER_ID");

        //构建请求收银台签名串，注意如果传参ext，也需参与签名

        //需要生成sign签名的参数
        String[] CASHIER = {"appKey", "merchantNo", "token", "timestamp", "directPayType", "cardType", "userNo", "userType"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CASHIER.length; i++) {
            String name = CASHIER[i];
            String value = params.get(name);
            if (i != 0) {
                sb.append("&");
            }
            sb.append(name).append("=").append(value);
        }

        String sign = signature(sb.toString(), privateKey);
        return cashierUrl + "?sign=" + sign + "&" + sb;

    }

    // 获取请求收银台的sign
    private static String signature(String plainText, PrivateKey privateKey) {
        byte[] data = plainText.getBytes(Charsets.UTF_8);
        DigestAlgEnum digestAlg = DigestAlgEnum.SHA256;
        byte[] sign = RSA.sign(data, privateKey, digestAlg);
        String signToBase64 = Encodes.encodeUrlSafeBase64(sign);
        return signToBase64 + "$" + digestAlg.getValue();
    }

}
