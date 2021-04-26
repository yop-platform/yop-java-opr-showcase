package com.yeepay.demo.shop.modules.order.enums;/**
 * Created by yp-tc-m-4855 on 2020/9/29.
 */

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/9/29上午10:28
 */
public enum PayOrderStatusEnum {

    /**
     * 初始化
     */
    INIT("初始化"),

    /**
     * 处理中
     */
    PROCESS("处理中"),

    /**
     * 成功
     */
    SUCCESS("成功"),

    /**
     * 失败
     */
    FAIL("失败");

    private String desc;

    PayOrderStatusEnum(String desc) {
        this.desc = desc;
    }
}
