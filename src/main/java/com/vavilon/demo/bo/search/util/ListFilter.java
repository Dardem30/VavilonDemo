package com.vavilon.demo.bo.search.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListFilter {
    private OrderBy sort;
    private int start;
    private int limit;
}
