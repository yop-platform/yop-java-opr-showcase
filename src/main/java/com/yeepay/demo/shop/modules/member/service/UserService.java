package com.yeepay.demo.shop.modules.member.service;

import com.yeepay.demo.shop.modules.member.entity.User;
import com.yeepay.demo.shop.support.OrderUtil;
import com.yeepay.demo.shop.support.PageUtil;

import java.util.List;

public interface UserService {

    User get(Integer user_id);

    User login(String user_name, String user_password);

    Integer getTotal(User user);
}
