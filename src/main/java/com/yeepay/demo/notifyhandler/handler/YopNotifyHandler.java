package com.yeepay.demo.notifyhandler.handler;

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/10/13下午6:42
 */
public interface YopNotifyHandler {

    /**
     * @param notifyStr
     * @throws Exception
     */
    void handle(String notifyStr) throws Exception;

}
