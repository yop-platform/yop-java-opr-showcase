package com.yeepay.yop.showcase.shop.modules.order.enums;

/**
 * @author jing.ju
 * @description
 * @date 2020/10/10 下午5:47
 */
public enum RefundStatus {

    SUCCESS("SUCCESS", "成功"),
    FAIL("FAIL", "失败"),
    PROCESSING("PROCESSING", "处理中"),
    ACCEPT("ACCEPT", "已接收");

    private String code;
    private String name;

    RefundStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
