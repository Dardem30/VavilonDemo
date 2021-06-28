package com.vavilon.demo.service;

import com.vavilon.demo.bo.Attachment;
import com.vavilon.demo.bo.bean.ResponseForm;
import com.vavilon.demo.bo.product.Product;
import com.vavilon.demo.bo.product.ProductCategory;
import com.vavilon.demo.bo.product.ProductOverviewItem;
import com.vavilon.demo.bo.search.ProductListFilter;
import com.vavilon.demo.bo.search.util.SearchResult;
import com.vavilon.demo.da.ProductRepository;
import com.vavilon.demo.service.util.GoogleDriveService;
import com.vavilon.demo.service.util.UtilityService;
import com.vavilon.demo.util.CommonUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UtilityService utilityService;
    private final GoogleDriveService googleDriveService;

    @Transactional
    public ResponseForm<Product> saveProduct(final Product product) {
        productRepository.save(product);
        return new ResponseForm<>("Product is successfully saved", true, product);
    }

    @Transactional(readOnly = true)
    public ResponseForm<SearchResult<ProductOverviewItem>> listProducts(final ProductListFilter listFilter) {
        final SearchResult<ProductOverviewItem> result = productRepository.listProducts(listFilter);
        return new ResponseForm<>("Success", true, result);
    }

    @Transactional(readOnly = true)
    public List<ProductCategory> productCategories() {
        return productRepository.productCategories();
    }

    @Transactional
    public void uploadProductAttachment(final MultipartFile inputFile, final Long productId) throws Exception {
        final String fileId = googleDriveService.uploadFile(inputFile, CommonUtils.addTimestampToFileName(inputFile.getOriginalFilename()));
        final Attachment attachment = new Attachment();
        attachment.setFileId(fileId);
        attachment.setProductId(productId);
        utilityService.mergeObject(attachment);
    }
}
