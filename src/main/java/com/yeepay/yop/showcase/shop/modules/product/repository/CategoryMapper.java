package com.yeepay.yop.showcase.shop.modules.product.repository;

import com.yeepay.yop.showcase.shop.modules.product.entity.Category;
import com.yeepay.yop.showcase.shop.support.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> select(@Param("category_name") String category_name, @Param("pageUtil") PageUtil pageUtil);

    Category selectOne(@Param("category_id") Integer category_id);

    Integer selectTotal(@Param("category_name") String category_name);
}