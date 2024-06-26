package com.vavilon.demo.da.extension;

import com.vavilon.demo.bo.Attachment;
import com.vavilon.demo.bo.product.ProductCategory;
import com.vavilon.demo.bo.product.ProductOverviewItem;
import com.vavilon.demo.bo.search.ProductListFilter;
import com.vavilon.demo.bo.search.util.SearchResult;

import java.util.List;

public interface ProductRepositoryExtension {
    SearchResult<ProductOverviewItem> listProducts(ProductListFilter listFilter);
    List<ProductCategory> productCategories();
    void resetMainPhotoToTheProduct(Long productId);
    List<Attachment> readProductAttachments(Long productId);
    void setAttachmentAsMainImage(Long attachmentId);
}
