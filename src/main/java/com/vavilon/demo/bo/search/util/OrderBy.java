package com.vavilon.demo.bo.search.util;

import com.vavilon.demo.bo.enums.SortDirection;
import lombok.Data;

@Data
public class OrderBy {
    private String property;
    private SortDirection direction;
}
