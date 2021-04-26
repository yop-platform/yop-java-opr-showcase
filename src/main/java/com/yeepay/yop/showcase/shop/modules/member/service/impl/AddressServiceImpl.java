package com.yeepay.yop.showcase.shop.modules.member.service.impl;

import com.yeepay.yop.showcase.shop.modules.member.entity.Address;
import com.yeepay.yop.showcase.shop.modules.member.repository.AddressMapper;
import com.yeepay.yop.showcase.shop.modules.member.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Resource(name = "addressMapper")
    private AddressMapper addressMapper;

    @Override
    public List<Address> getList(String address_name, String address_regionId) {
        return addressMapper.select(address_name, address_regionId);
    }

    @Override
    public Address get(String address_areaId) {
        return addressMapper.selectOne(address_areaId);
    }

    @Override
    public List<Address> getRoot() {
        return addressMapper.selectRoot();
    }
}
