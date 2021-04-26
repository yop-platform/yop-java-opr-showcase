package com.yeepay.yop.showcase.shop.modules.product.repository;

import com.yeepay.yop.showcase.shop.modules.product.entity.ProductImage;
import com.yeepay.yop.showcase.shop.support.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductImageMapper {
    Integer insertOne(@Param("productImage") ProductImage productImage);

    Integer insertList(@Param("productImage_list") List<ProductImage> productImageList);

    Integer updateOne(@Param("productImage") ProductImage productImage);

    Integer deleteList(@Param("productImage_id_list") Integer[] productImage_id_list);

    List<ProductImage> select(@Param("product_id") Integer product_id, @Param("productImage_type") Byte productImage_type, @Param("pageUtil") PageUtil pageUtil);

    ProductImage selectOne(@Param("productImage_id") Integer productImage_id);

    Integer selectTotal(@Param("product_id") Integer product_id, @Param("productImage_type") Byte productImage_type);
}
