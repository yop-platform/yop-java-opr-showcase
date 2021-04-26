package com.yeepay.yop.showcase.shop.modules.member.service;

import com.yeepay.yop.showcase.shop.modules.member.entity.User;

public interface UserService {

    User get(Integer user_id);

    User login(String user_name, String user_password);

    Integer getTotal(User user);
}
