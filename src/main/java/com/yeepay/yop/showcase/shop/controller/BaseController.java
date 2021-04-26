package com.yeepay.yop.showcase.shop.controller;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

/**
 * 基控制器
 */
public class BaseController {

    protected Logger logger = LogManager.getLogger(BaseController.class);

    protected JsonMapper jsonMapper = JsonMapper.builder().build();

    //检查用户是否登录
    protected Object checkUser(HttpSession session) {
        logger.debug("检查用户是否登录");
        Object o = session.getAttribute("userId");
        if (o == null) {
            logger.debug("用户未登录");
        }
        return o;
    }
}
