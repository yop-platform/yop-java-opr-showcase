package com.yeepay.yop.showcase.shop.modules.member.repository;

import com.yeepay.yop.showcase.shop.modules.member.entity.User;
import com.yeepay.yop.showcase.shop.support.OrderUtil;
import com.yeepay.yop.showcase.shop.support.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    Integer insertOne(@Param("user") User user);

    Integer updateOne(@Param("user") User user);

    List<User> select(@Param("user") User user, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);

    User selectOne(@Param("user_id") Integer user_id);

    User selectByLogin(@Param("user_name") String user_name, @Param("user_password") String user_password);

    Integer selectTotal(@Param("user") User user);
}
