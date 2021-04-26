package com.yeepay.demo.shop.modules.order.controller;

import com.alibaba.fastjson.JSON;
import com.yeepay.demo.shop.modules.order.biz.PayOrderQueryBizService;
import com.yeepay.demo.shop.modules.order.biz.RefundBizService;
import com.yeepay.demo.shop.modules.order.entity.PayOrder;
import com.yeepay.demo.shop.modules.order.service.PayOrderService;
import com.yeepay.demo.notifyhandler.vo.OrderNotifyBean;
import com.yeepay.demo.shop.modules.order.enums.YopRefundStatus;
import com.yeepay.demo.shop.modules.order.service.PayOrderRequestService;
import com.yeepay.demo.shop.modules.order.vo.PayOrderQueryRequestDTO;
import com.yeepay.demo.shop.modules.order.vo.PayOrderRequestDTO;
import com.yeepay.demo.shop.modules.order.vo.RefundRequestDTO;
import com.yeepay.demo.shop.modules.order.vo.RefundResponseDTO;
import com.yeepay.demo.shop.modules.product.entity.ProductOrder;
import com.yeepay.demo.shop.modules.product.entity.ProductOrderItem;
import com.yeepay.demo.shop.support.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author jing.ju
 * @description
 * @date 2020/9/9 上午10:58
 */
@Controller
@RequestMapping("/payOrder")
public class PayOrderController {
    private static final Logger logger = LoggerFactory.getLogger(PayOrderController.class);

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private PayOrderRequestService payOrderRequestService;

    @Autowired
    private RefundBizService refundBizService;

    @Autowired
    private PayOrderQueryBizService payOrderQueryBizService;

    @RequestMapping("/list")
    public String list(ModelMap modelMap) {
        List<PayOrder> orderList = payOrderService.queryAllPayOrder();
        modelMap.addAttribute("orderList", orderList);
        return "order/payOrder";
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public Map updateStatus(@RequestParam String orderId) {
        logger.info("orderId{}", orderId);
        Map<String, String> res = new HashMap<>();
        res.put("code", "200");

        PayOrderQueryRequestDTO requestDTO = new PayOrderQueryRequestDTO();
        requestDTO.setRequestNo(orderId);

        OrderNotifyBean responseDTO = payOrderQueryBizService.payOrderQueryRequest(requestDTO);
        if (!Constant.YOP_TRADE_ORDER_SUCCESS_TYPE.equals(responseDTO.getCode())) {
            res.put("code", "500");
            res.put("msg", responseDTO.getMessage());
        }
        return res;
    }

    @RequestMapping("/request")
    public Object request(HttpServletRequest request) {
        PayOrderRequestDTO requestDTO = new PayOrderRequestDTO();
        requestDTO.setIndustrySolutionId(1);
        requestDTO.setGoodsName("商品");
        requestDTO.setRequestNo(System.currentTimeMillis() + "");
        requestDTO.setAmount(new BigDecimal("1"));
        requestDTO.setCreateTime(new Date());
        payOrderRequestService.payOrderRequest(requestDTO);
        return "";
    }

    private BigDecimal getOrderAmount(ProductOrder order) {
        BigDecimal orderAmount = new BigDecimal("0");
        List<ProductOrderItem> productOrderItemList = order.getProductOrderItemList();
        for (ProductOrderItem productOrderItem : productOrderItemList) {
            logger.info("获取订单信息为{}", JSON.toJSONString(productOrderItem));
            BigDecimal price = BigDecimal.valueOf(productOrderItem.getProductOrderItem_price());
            BigDecimal number = BigDecimal.valueOf(productOrderItem.getProductOrderItem_number());
            price = price.multiply(number);
            orderAmount = orderAmount.add(price);
        }
        return orderAmount;
    }

    @RequestMapping("/refund")
    @ResponseBody
    public Map refund(@RequestParam String payUniqueOrderNo, @RequestParam String orderId) {
        Map<String, String> res = new HashMap<>();
        res.put("code", "200");

        RefundRequestDTO refundRequestDTO = new RefundRequestDTO();
        refundRequestDTO.setRequestNo(UUID.randomUUID().toString());
        refundRequestDTO.setPayOrderId(orderId);
        refundRequestDTO.setPayUniqueOrderNo(payUniqueOrderNo);

        RefundResponseDTO refundResponseDTO = refundBizService.refundRequest(refundRequestDTO);
        if (YopRefundStatus.SUCCESS.name().equals(refundResponseDTO.getStatus())) {
            res.put("msg", "退款成功！");
        } else if (YopRefundStatus.PROCESSING.name().equals(refundResponseDTO.getStatus())) {
            res.put("msg", "退款处理中，请稍后查询！");
        } else if (Constant.REFUND_ERROR.equals(refundResponseDTO.getCode())) {
            logger.error("refund error, detail:{}", refundResponseDTO.getMessage());
            res.put("code", "500");
            res.put("msg", refundResponseDTO.getMessage());
        } else {
            res.put("msg", "退款失败！" + refundResponseDTO.getMessage());
        }
        return res;
    }
}
