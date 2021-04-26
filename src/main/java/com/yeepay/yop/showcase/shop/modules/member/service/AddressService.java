package com.yeepay.yop.showcase.shop.modules.member.service;


import com.yeepay.yop.showcase.shop.modules.member.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> getList(String address_name, String address_regionId);

    Address get(String address_areaId);

    List<Address> getRoot();
}
