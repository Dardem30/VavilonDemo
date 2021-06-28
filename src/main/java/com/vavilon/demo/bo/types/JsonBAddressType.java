package com.vavilon.demo.bo.types;

import com.vavilon.demo.bo.bean.Address;

public class JsonBAddressType extends JsonBType {
    @Override
    public Class<Address> returnedClass() {
        return Address.class;
    }
}