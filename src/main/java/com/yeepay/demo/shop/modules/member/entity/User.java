package com.yeepay.demo.shop.modules.member.entity;

import com.yeepay.demo.shop.modules.product.entity.ProductOrder;
import com.yeepay.demo.shop.modules.product.entity.ProductOrderItem;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 用户实体类
 *
 * @author
 */
@Data
public class User {

    private Integer user_id/*用户ID*/;
    private String user_name/*用户登录名*/;
    private String user_nickname/*用户昵称*/;
    private String user_password/*用户密码*/;
    private String user_realname/*用户姓名*/;
    private Byte user_gender/*用户性别*/;
    private Date user_birthday/*用户生日*/;
    private Address user_address/*用户现居地*/;
    private Address user_homeplace/*用户家乡地址*/;
    private String user_profile_picture_src/*用户头像路径*/;
    private List<ProductOrderItem> productOrderItemList/*订单项（购物车）集合*/;
    private List<ProductOrder> productOrderList/*订单集合*/;

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_nickname='" + user_nickname + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_realname='" + user_realname + '\'' +
                ", user_gender=" + user_gender +
                ", user_birthday=" + user_birthday +
                ", user_address=" + user_address +
                ", user_homeplace=" + user_homeplace +
                ", user_profile_picture_src='" + user_profile_picture_src + '\'' +
                '}';
    }

}
