package com.yeepay.yop.showcase.shop.modules.member.service.impl;

import com.yeepay.yop.showcase.shop.modules.member.entity.User;
import com.yeepay.yop.showcase.shop.modules.member.repository.UserMapper;
import com.yeepay.yop.showcase.shop.modules.member.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Override
    public User get(Integer user_id) {
        return userMapper.selectOne(user_id);
    }

    @Override
    public User login(String user_name, String user_password) {
        return userMapper.selectByLogin(user_name, user_password);
    }

    @Override
    public Integer getTotal(User user) {
        return userMapper.selectTotal(user);
    }
}
