package com.vavilon.demo.da.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.ParameterExpression;

@Getter
@Setter
@AllArgsConstructor
public class Parameter<T> {
    private ParameterExpression<T> parameter;
    private T value;
}
