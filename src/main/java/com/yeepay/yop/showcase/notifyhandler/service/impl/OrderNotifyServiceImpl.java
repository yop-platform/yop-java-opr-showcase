package com.yeepay.yop.showcase.notifyhandler.service.impl;

import com.alibaba.fastjson.JSON;
import com.yeepay.yop.showcase.shop.modules.order.entity.PayOrder;
import com.yeepay.yop.showcase.shop.modules.order.service.PayOrderService;
import com.yeepay.yop.showcase.notifyhandler.service.NotifyService;
import com.yeepay.yop.showcase.notifyhandler.vo.NotifyBean;
import com.yeepay.yop.showcase.notifyhandler.vo.OrderNotifyBean;
import com.yeepay.yop.showcase.notifyhandler.vo.PayerInfoBean;
import com.yeepay.yop.showcase.shop.modules.order.enums.PayOrderStatusEnum;
import com.yeepay.yop.showcase.shop.modules.product.entity.Product;
import com.yeepay.yop.showcase.shop.modules.product.entity.ProductOrder;
import com.yeepay.yop.showcase.shop.modules.product.entity.ProductOrderItem;
import com.yeepay.yop.showcase.shop.modules.product.service.CategoryService;
import com.yeepay.yop.showcase.shop.modules.product.service.ProductOrderItemService;
import com.yeepay.yop.showcase.shop.modules.product.service.ProductOrderService;
import com.yeepay.yop.showcase.shop.modules.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author hao.zhang-2
 * @version V1.0.0
 * @date 2020/9/29下午3:14
 */
@Slf4j
@Service
public class OrderNotifyServiceImpl implements NotifyService {

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductOrderItemService productOrderItemService;

    /**
     * 1 更新支付订单
     * 2 更新其他支付订单为支付失败
     * 3 更新产品订单
     *
     * @param notifyBean
     */
    @Override
    public void processNotifyInfo(NotifyBean notifyBean) {
        OrderNotifyBean orderNotifyBean = (OrderNotifyBean) notifyBean;
        PayOrder payOrder = payOrderService.queryPayOrderByOrderId(orderNotifyBean.getOrderId());
        if (!PayOrderStatusEnum.PROCESS.name().equals(payOrder)) {
            updatePayOrder(orderNotifyBean, payOrder);
            updateOtherPayOrder(payOrder);
//            updateProductOrder(payOrder);
        }
    }

    private void updateOtherPayOrder(PayOrder payOrder) {
        List<PayOrder> payOrderList = payOrderService.queryPayOrderByProductOrderId(payOrder.getProductOrderId(), payOrder.getOrderId());
        for (PayOrder order : payOrderList) {
            order.setStatus(PayOrderStatusEnum.FAIL.name());
            order.setLastUpdateTime(new Date());
            payOrderService.update(order);
        }
    }

    private void updateProductOrder(PayOrder payOrder) {
        ProductOrder order = productOrderService.getByCode(payOrder.getProductOrderId());
        order.setProductOrderItemList(productOrderItemService.getListByOrderId(order.getProductOrder_id(), null));
        double orderTotalPrice = 0.00;
        if (order.getProductOrderItemList().size() == 1) {
            log.info("获取单订单项的产品信息");
            ProductOrderItem productOrderItem = order.getProductOrderItemList().get(0);
            Product product = productService.get(productOrderItem.getProductOrderItem_product().getProduct_id());
            product.setProduct_category(categoryService.get(product.getProduct_category().getCategory_id()));
            productOrderItem.setProductOrderItem_product(product);
            orderTotalPrice = productOrderItem.getProductOrderItem_price();
            log.info("更新产品销量信息");
            Product updateProduct = new Product()
                    .setProduct_id(product.getProduct_id())
                    .setProduct_sale_count(product.getProduct_sale_count() + productOrderItem.getProductOrderItem_number());
            log.info("更新产品信息，产品ID值为：{}", product.getProduct_id());
            boolean yn = productService.update(updateProduct);
            log.info("产品销量信息更新成功！");
        } else {
            for (ProductOrderItem productOrderItem : order.getProductOrderItemList()) {
                Product product = productService.get(productOrderItem.getProductOrderItem_product().getProduct_id());
                log.info("更新产品销量信息");
                Product updateProduct = new Product()
                        .setProduct_id(product.getProduct_id())
                        .setProduct_sale_count(product.getProduct_sale_count() + productOrderItem.getProductOrderItem_number());
                log.info("更新产品信息，产品ID值为：{}", product.getProduct_id());
                boolean yn = productService.update(updateProduct);
                log.info("产品销量信息更新成功！");
                orderTotalPrice += productOrderItem.getProductOrderItem_price();
            }
        }
        log.info("总共支付金额为：{}元", orderTotalPrice);
        log.info("更新订单信息");
        ProductOrder productOrder = new ProductOrder()
                .setProductOrder_id(order.getProductOrder_id())
                .setProductOrder_pay_date(new Date())
                .setProductOrder_status((byte) 1);
        productOrderService.update(productOrder);
    }

    private void updatePayOrder(OrderNotifyBean orderNotifyBean, PayOrder payOrder) {
        String payerInfo = orderNotifyBean.getPayerInfo();
        PayerInfoBean payerInfoBean = JSON.parseObject(payerInfo, PayerInfoBean.class);
        BeanUtils.copyProperties(orderNotifyBean, payOrder);
        BeanUtils.copyProperties(payerInfoBean, payOrder);
        log.info("支付订单结果为{}", JSON.toJSONString(payOrder));
        payOrderService.update(payOrder);
    }

}
