package com.yeepay.demo.shop.modules.order.biz;

import com.yeepay.demo.notifyhandler.vo.OrderNotifyBean;
import com.yeepay.demo.shop.modules.order.vo.PayOrderQueryRequestDTO;

/**
 * @author jing.ju
 * @description
 * @date 2020/10/13 下午2:14
 */
public interface PayOrderQueryBizService {
    OrderNotifyBean payOrderQueryRequest(PayOrderQueryRequestDTO requestDTO);
}
