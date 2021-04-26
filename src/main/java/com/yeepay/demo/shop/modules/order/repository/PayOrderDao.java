package com.yeepay.demo.shop.modules.order.repository;

import com.yeepay.demo.shop.modules.order.entity.PayOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author jing.ju
 * @description
 * @date 2020/9/25 上午11:07
 */
@Mapper
public interface PayOrderDao {

    /**
     * 新增支付表
     *
     * @param payOrder
     * @return
     */
    void insert(PayOrder payOrder);

    /**
     * 更新支付表
     *
     * @param payOrder
     */
    void update(PayOrder payOrder);

    /**
     * @return
     */
    List<PayOrder> getAll();

    /**
     * 根据订单ID查询支付订单
     *
     * @param orderId
     * @return
     */
    PayOrder queryPayOrderByOrderId(@Param("orderId") String orderId);


    List<PayOrder> queryPayOrderByProductOrderId(@Param("productOrderId") String productOrderId,
                                                 @Param("orderId") String orderId);

    int updataStatus(@Param("status") String status,
                     @Param("orderId") String orderId);

    PayOrder queryByYbUniqueOrderNo(@Param("uniqueOrderNo") String uniqueOrderNo);

    int updataOrderToSuccess(@Param("uniqueOrderNo") String uniqueOrderNo,
                             @Param("paySuccessDate") Date paySuccessDate,
                             @Param("bankOrderId") String bankOrderId,
                             @Param("merchantFee") BigDecimal merchantFee,
                             @Param("customerFee") BigDecimal customerFee,
                             @Param("orderId") String orderId);


    int updataOrderToTimeOut(@Param("errorMsg") String errorMsg,
                             @Param("uniqueOrderNo") String uniqueOrderNo,
                             @Param("orderId") String orderId);
}
