package com.yeepay.demo.shop.modules.product.repository;

import com.yeepay.demo.shop.modules.order.entity.OrderGroup;
import com.yeepay.demo.shop.modules.product.entity.ProductOrder;
import com.yeepay.demo.shop.support.OrderUtil;
import com.yeepay.demo.shop.support.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ProductOrderMapper {
    Integer insertOne(@Param("productOrder") ProductOrder productOrder);

    Integer updateOne(@Param("productOrder") ProductOrder productOrder);

    Integer deleteList(@Param("productOrder_id_list") Integer[] productOrder_id_list);

    List<ProductOrder> select(@Param("productOrder") ProductOrder productOrder, @Param("productOrder_status_array") Byte[] productOrder_status_array, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);

    ProductOrder selectOne(@Param("productOrder_id") Integer productOrder_id);

    ProductOrder selectByCode(@Param("productOrder_code") String productOrder_code);

    Integer selectTotal(@Param("productOrder") ProductOrder productOrder, @Param("productOrder_status_array") Byte[] productOrder_status_array);

    List<OrderGroup> getTotalByDate(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
}
