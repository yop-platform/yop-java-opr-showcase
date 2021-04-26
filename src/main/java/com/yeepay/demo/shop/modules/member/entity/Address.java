package com.yeepay.demo.shop.modules.member.entity;

/**
 * 地址实体类
 *
 * @author
 */
public class Address {

    private String address_areaId/*地址ID*/;

    private String address_name/*地址名称*/;

    private Address address_regionId/*父级地址ID*/;

    @Override
    public String toString() {
        return "Address{" +
                "address_areaId='" + address_areaId + '\'' +
                ", address_name='" + address_name + '\'' +
                ", address_regionId=" + address_regionId +
                '}';
    }

    public String getAddress_areaId() {
        return address_areaId;
    }

    public Address setAddress_areaId(String address_areaId) {
        this.address_areaId = address_areaId;
        return this;
    }

    public String getAddress_name() {
        return address_name;
    }

    public Address setAddress_name(String address_name) {
        this.address_name = address_name;
        return this;
    }

    public Address getAddress_regionId() {
        return address_regionId;
    }

    public Address setAddress_regionId(Address address_regionId) {
        this.address_regionId = address_regionId;
        return this;
    }
}
