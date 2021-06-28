package com.vavilon.demo.bo.search;

import com.vavilon.demo.bo.search.util.ListFilter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductListFilter extends ListFilter {
    private Long productCategoryId;
}
