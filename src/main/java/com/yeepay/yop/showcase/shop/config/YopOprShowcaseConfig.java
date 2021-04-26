package com.yeepay.yop.showcase.shop.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/9/29上午9:41
 */
@Data
@Configuration
public class YopOprShowcaseConfig {

    @Value("${yop.parentMerchantNo}")
    private String parentMerchantNo;

    @Value("${yop.merchantNo}")
    private String merchantNo;

    @Value("${yop.appKey}")
    private String appKey;

    @Value("${yop.privateKey}")
    private String privateKey;

    @Value("${yop.publicKey}")
    private String publicKey;

    @Value("${yop.payNotifyUrl}")
    private String payNotifyUrl;

    @Value("${yop.refundNotifyUrl}")
    private String refundNotifyUrl;

    @Value("${yop.redirectUrl}")
    private String redirectUrl;

}
