package com.yeepay.yop.showcase.shop.modules.order.vo;/**
 * Created by yp-tc-m-4855 on 2020/9/28.
 */

import lombok.Data;

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/9/28下午3:04
 */
@Data
public class ResponseResult {
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误信息
     */
    private String message;

    private String token;

    private String uniqueOrderNo;
}
