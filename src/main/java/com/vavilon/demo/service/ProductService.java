package com.vavilon.demo.service;

import com.vavilon.demo.bo.Attachment;
import com.vavilon.demo.bo.bean.ResponseForm;
import com.vavilon.demo.bo.product.Product;
import com.vavilon.demo.bo.product.ProductCategory;
import com.vavilon.demo.bo.product.ProductOverviewItem;
import com.vavilon.demo.bo.search.ProductListFilter;
import com.vavilon.demo.bo.search.util.SearchResult;
import com.vavilon.demo.da.ProductRepository;
import com.vavilon.demo.service.security.User;
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
        product.setUserId(User.getCurrentLoggedInUser().getUserId());
        productRepository.save(product);
        return new ResponseForm<>("Product is successfully saved", true, product);
    }

    @Transactional(readOnly = true)
    public ResponseForm<SearchResult<ProductOverviewItem>> listProducts(final ProductListFilter listFilter) {
        listFilter.setUserId(User.get().getAppUser().getUserId());
        final SearchResult<ProductOverviewItem> result = productRepository.listProducts(listFilter);
        return new ResponseForm<>("Success", true, result);
    }

    @Transactional(readOnly = true)
    public List<ProductCategory> productCategories() {
        return productRepository.productCategories();
    }

    @Transactional
    public void uploadProductAttachment(final MultipartFile inputFile, final Long productId, final boolean mainPhoto) throws Exception {
        final String fileId = googleDriveService.uploadFile(inputFile, CommonUtils.addTimestampToFileName(inputFile.getOriginalFilename()));
        final Attachment attachment = new Attachment();
        attachment.setFileId(fileId);
        attachment.setProductId(productId);
        if (mainPhoto) {
            attachment.setMain(true);
            productRepository.resetMainPhotoToTheProduct(productId);
        }
        utilityService.mergeObject(attachment);
    }

    @Transactional(readOnly = true)
    public List<Product> getUserProducts(final Long userId) {
        return productRepository.findAllByUserId(userId);
    }

    @Transactional(readOnly = true)
    public Product readProduct(final Long productId) {
        return productRepository.findById(productId).get();
    }

    @Transactional(readOnly = true)
    public List<Attachment> readProductAttachments(final Long productId) {
        return productRepository.readProductAttachments(productId);
    }

    public void deleteAttachmentFile(final Attachment attachment) throws Exception {
        googleDriveService.deleteFile(attachment.getFileId());
    }

    @Transactional
    public void updateMainPhotoForProduct(final Long productId, final Long attachmentId) {
        productRepository.resetMainPhotoToTheProduct(productId);
        productRepository.setAttachmentAsMainImage(attachmentId);
    }
}
