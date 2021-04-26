package com.yeepay.demo.notifyhandler.handler.impl;

import com.alibaba.fastjson.JSON;
import com.yeepay.demo.notifyhandler.handler.BaseYopNotifyHandler;
import com.yeepay.demo.notifyhandler.handler.NotifyHandlerFactory;
import com.yeepay.demo.notifyhandler.service.NotifyService;
import com.yeepay.demo.notifyhandler.vo.OrderNotifyBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/10/13下午7:16
 */
@Service
public class YopPaymentNotifyHandler extends BaseYopNotifyHandler {

    @Qualifier("orderNotifyServiceImpl")
    private NotifyService orderNotifyService;

    @PostConstruct
    public void init() {
        NotifyHandlerFactory.register("/orderNotifyCallback", this);
    }

    @Override
    public void doHandle(String notifyInfo) {
        OrderNotifyBean orderNotifyBean = JSON.parseObject(notifyInfo, OrderNotifyBean.class);
        orderNotifyService.processNotifyInfo(orderNotifyBean);
    }

}
