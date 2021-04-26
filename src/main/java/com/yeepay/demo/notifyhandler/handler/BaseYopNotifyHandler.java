/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */

package com.yeepay.demo.notifyhandler.handler;

/**
 * title: <br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author dreambt
 * @version 1.0.0
 * @since 2020/11/5 17:33
 */
public abstract class BaseYopNotifyHandler implements YopNotifyHandler {

    @Override
    public void handle(String notifyStr) {
        // TODO 验签

        doHandle(notifyStr);
    }

    protected abstract void doHandle(String notifyStr);

}
