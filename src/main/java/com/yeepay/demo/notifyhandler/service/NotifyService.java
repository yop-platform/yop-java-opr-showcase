package com.yeepay.demo.notifyhandler.service;

import com.yeepay.demo.notifyhandler.vo.NotifyBean;

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/9/29上午11:30
 */
public interface NotifyService {

    /**
     * 处理通知信息
     *
     * @param notifyBean
     */
    void processNotifyInfo(NotifyBean notifyBean);

}
