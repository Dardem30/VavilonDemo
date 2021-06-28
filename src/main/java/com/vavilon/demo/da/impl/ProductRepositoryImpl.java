package com.vavilon.demo.da.impl;

import com.vavilon.demo.bo.product.ProductCategory;
import com.vavilon.demo.bo.product.ProductOverviewItem;
import com.vavilon.demo.bo.product.ProductOverviewItem_;
import com.vavilon.demo.bo.search.ProductListFilter;
import com.vavilon.demo.bo.search.util.SearchResult;
import com.vavilon.demo.da.base.BaseRepository;
import com.vavilon.demo.da.extension.ProductRepositoryExtension;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl extends BaseRepository implements ProductRepositoryExtension {
    @Override
    public SearchResult<ProductOverviewItem> listProducts(ProductListFilter listFilter) {
        return resolvePredicates(ProductOverviewItem.class, listFilter, (root, builder) -> {
            final List<Predicate> predicates = new ArrayList<>(1);
            if (listFilter.getProductCategoryId() != null) {
               predicates.add(builder.equal(root.get(ProductOverviewItem_.categoryId), listFilter.getProductCategoryId()));
            }
            return predicates;
        });
    }

    @Override
    public List<ProductCategory> productCategories() {
        return findAll(ProductCategory.class);
    }
}
