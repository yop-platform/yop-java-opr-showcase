package com.yeepay.yop.showcase.shop.modules.product.service;


import com.yeepay.yop.showcase.shop.modules.product.entity.Category;
import com.yeepay.yop.showcase.shop.support.PageUtil;

import java.util.List;

public interface CategoryService {

    List<Category> getList(String category_name, PageUtil pageUtil);

    Category get(Integer category_id);

    Integer getTotal(String category_name);
}
