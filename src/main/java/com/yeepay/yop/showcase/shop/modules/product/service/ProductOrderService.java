package com.yeepay.yop.showcase.shop.modules.product.service;

import com.yeepay.yop.showcase.shop.modules.order.entity.OrderGroup;
import com.yeepay.yop.showcase.shop.modules.product.entity.ProductOrder;
import com.yeepay.yop.showcase.shop.support.OrderUtil;
import com.yeepay.yop.showcase.shop.support.PageUtil;

import java.util.Date;
import java.util.List;

public interface ProductOrderService {
    boolean add(ProductOrder productOrder);

    boolean update(ProductOrder productOrder);

    boolean deleteList(Integer[] productOrder_id_list);

    List<ProductOrder> getList(ProductOrder productOrder, Byte[] productOrder_status_array, OrderUtil orderUtil, PageUtil pageUtil);

    List<OrderGroup> getTotalByDate(Date beginDate, Date endDate);

    ProductOrder get(Integer productOrder_id);

    ProductOrder getByCode(String productOrder_code);

    Integer getTotal(ProductOrder productOrder, Byte[] productOrder_status_array);
}
