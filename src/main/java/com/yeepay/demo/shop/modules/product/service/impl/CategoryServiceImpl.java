package com.yeepay.demo.shop.modules.product.service.impl;

import com.yeepay.demo.shop.modules.product.entity.Category;
import com.yeepay.demo.shop.modules.product.repository.CategoryMapper;
import com.yeepay.demo.shop.modules.product.service.CategoryService;
import com.yeepay.demo.shop.support.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper categoryMapper;

    @Resource(name = "categoryMapper")
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> getList(String category_name, PageUtil pageUtil) {
        return categoryMapper.select(category_name, pageUtil);
    }

    @Override
    public Category get(Integer category_id) {
        return categoryMapper.selectOne(category_id);
    }

    @Override
    public Integer getTotal(String category_name) {
        return categoryMapper.selectTotal(category_name);
    }
}
