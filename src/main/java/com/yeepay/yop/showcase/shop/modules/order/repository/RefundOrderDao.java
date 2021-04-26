package com.yeepay.yop.showcase.shop.modules.order.repository;

import com.yeepay.yop.showcase.shop.modules.order.entity.RefundOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author jing.ju
 * @description
 * @date 2020/9/27 下午4:22
 */
@Mapper
public interface RefundOrderDao {

    int updataOrderToProcssing(@Param("uniqueRefundNo") String uniqueRefundNo,
                               @Param("refundRequestId") String refundRequestId);

    int updataOrderToSuccess(@Param("refundSuccessDate") Date refundSuccessDate,
                             @Param("uniqueRefundNo") String uniqueRefundNo,
                             @Param("refundRequestId") String refundRequestId,
                             @Param("realRefundAmount") BigDecimal realRefundAmount);

    int updataOrderToFail(@Param("errorCode") String errorCode,
                          @Param("errorMsg") String errorMsg,
                          @Param("uniqueRefundNo") String uniqueRefundNo,
                          @Param("refundRequestId") String refundRequestId);

    void insert(RefundOrder refundOrder);

    RefundOrder queryByRefundRequestId(@Param("refundRequestId") String refundRequestId);

    List<RefundOrder> getAll();
}
