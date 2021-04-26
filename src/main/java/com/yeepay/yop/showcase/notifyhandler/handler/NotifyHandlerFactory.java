/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */

package com.yeepay.yop.showcase.notifyhandler.handler;

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
 * @since 2020/11/5 17:21
 */
public class NotifyHandlerFactory {

    private static final Map<String, YopNotifyHandler> yopNotifyHandlerMap = new HashMap<>();

    public static void register(String url, YopNotifyHandler yopNotifyHandler) {
        yopNotifyHandlerMap.computeIfAbsent(url, s -> yopNotifyHandler);
    }

    public static YopNotifyHandler get(String url) {
        return yopNotifyHandlerMap.get(url);
    }

}
