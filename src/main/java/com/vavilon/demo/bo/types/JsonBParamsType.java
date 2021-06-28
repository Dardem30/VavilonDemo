package com.vavilon.demo.bo.types;

import com.vavilon.demo.bo.bean.Params;

public class JsonBParamsType extends JsonBType {
    @Override
    public Class<Params> returnedClass() {
        return Params.class;
    }
}