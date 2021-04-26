package com.yeepay.demo.notifyhandler.handler.impl;

import com.alibaba.fastjson.JSON;
import com.yeepay.demo.notifyhandler.handler.BaseYopNotifyHandler;
import com.yeepay.demo.notifyhandler.handler.NotifyHandlerFactory;
import com.yeepay.demo.notifyhandler.service.NotifyService;
import com.yeepay.demo.notifyhandler.vo.RefundNotifyBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/10/13下午7:16
 */
@Service
public class YopRefundNotifyHandler extends BaseYopNotifyHandler {

    @Qualifier("refundNotifyServiceImpl")
    private NotifyService refundNotifyService;

    @PostConstruct
    public void init() {
        NotifyHandlerFactory.register("/refundNotifyCallback", this);
    }

    @Override
    public void doHandle(String notifyInfo) {
        RefundNotifyBean refundNotifyBean = JSON.parseObject(notifyInfo, RefundNotifyBean.class);
        refundNotifyService.processNotifyInfo(refundNotifyBean);
    }

}
