package com.yeepay.demo.shop.modules.order.controller;

import com.yeepay.demo.shop.modules.order.biz.RefundQueryBizService;
import com.yeepay.demo.shop.modules.order.entity.RefundOrder;
import com.yeepay.demo.shop.modules.order.service.RefundOrderService;
import com.yeepay.demo.shop.modules.order.vo.RefundQueryRequestDTO;
import com.yeepay.demo.shop.modules.order.vo.RefundQueryResponseDTO;
import com.yeepay.demo.shop.support.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jing.ju
 * @description
 * @date 2020/9/9 上午10:58
 */
@Slf4j
@Controller
@RequestMapping("/refundOrder")
public class RefundOrderController {

    @Autowired
    private RefundOrderService refundOrderService;

    @Autowired
    private RefundQueryBizService refundQueryBizService;

    @RequestMapping("/list")
    public String list(ModelMap modelMap) {
        List<RefundOrder> orderList = refundOrderService.queryAllRefundOrder();
        modelMap.addAttribute("orderList", orderList);
        return "order/refundOrder";
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public Map updateStatus(@RequestParam String payOrderId, @RequestParam String uniqueRefundNo,
                            @RequestParam String refundRequestId) {
        Map<String, String> res = new HashMap<>();
        res.put("code", "200");

        RefundQueryRequestDTO refundQueryRequestDTO = new RefundQueryRequestDTO();
        refundQueryRequestDTO.setPayOrderId(payOrderId);
        refundQueryRequestDTO.setRefundRequestId(refundRequestId);
        refundQueryRequestDTO.setUniqueRefundNo(uniqueRefundNo);
        RefundQueryResponseDTO refundQueryResponseDTO = refundQueryBizService.refundQueryRequest(refundQueryRequestDTO);

        if (!Constant.YOP_TRADE_ORDER_SUCCESS_TYPE.equals(refundQueryResponseDTO.getCode())) {
            res.put("code", "500");
            res.put("msg", refundQueryResponseDTO.getMessage());
        }
        return res;
    }
}
