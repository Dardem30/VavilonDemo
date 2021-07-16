package com.vavilon.demo.controller;

import com.vavilon.demo.bo.bean.ResponseForm;
import com.vavilon.demo.bo.product.Product;
import com.vavilon.demo.bo.product.ProductCategory;
import com.vavilon.demo.bo.product.ProductOverviewItem;
import com.vavilon.demo.bo.search.ProductListFilter;
import com.vavilon.demo.bo.search.util.SearchResult;
import com.vavilon.demo.bo.user.AppUser;
import com.vavilon.demo.service.ProductService;
import com.vavilon.demo.service.security.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
@AllArgsConstructor
public class ProductController extends CommonController {
    private final ProductService productService;

    @PostMapping(path = "/saveProduct")
    public void saveProduct(@RequestBody final Product product, final HttpServletResponse response) {
        try {
            final ResponseForm<Product> form = productService.saveProduct(product);
            writeResponseAsJSON(form, response, (content, savedProduct) -> {
                content.put("productId", savedProduct.getProductId());
            });
        } catch (final Exception e) {
            logger.error("Failed to save product", e);
            writeResponseAsJSON(new ResponseForm<>("Failed to save product", false), response, null);
        }
    }

    @PostMapping(path = "/uploadProductAttachment")
    public void uploadProductAttachment(@RequestPart("inputFiles") final MultipartFile[] inputFiles,
                                        @RequestParam(required = true) final Long productId,
                                        @RequestParam(required = false) Long indexOfMainPhoto,
                                        final HttpServletResponse response) {
        try {
            if (indexOfMainPhoto == null) {
                indexOfMainPhoto = -1L;
            }
            for (int index = 0; index < inputFiles.length; index++) {
                final MultipartFile inputFile = inputFiles[index];
                productService.uploadProductAttachment(inputFile, productId, index == indexOfMainPhoto);
            }
            writeResponseAsJSON(new ResponseForm<>("The files are uploaded successfully", true), response, null);
        } catch (final Exception e) {
            logger.error("Failed to upload the files for product: " + productId, e);
            writeResponseAsJSON(new ResponseForm<>("Failed to upload the files", false), response, null);
        }
    }

    @PostMapping(path = "/listProducts")
    public void listProduct(@RequestBody final ProductListFilter listFilter, final HttpServletResponse response) {
        try {
            final ResponseForm<SearchResult<ProductOverviewItem>> form = productService.listProducts(listFilter);
            writeResponseAsJSON(form, response, (content, result) -> {
                content.put("result", result.getResult());
                content.put("total", result.getTotalNumberFound());
            });
        } catch (final Exception e) {
            logger.error("Failed to search product", e);
            writeResponseAsJSON(new ResponseForm<>("Failed to search product", false), response, null);
        }
    }

    @GetMapping(path = "/productCategories")
    public @ResponseBody
    List<ProductCategory> productCategories() {
        return productService.productCategories();
    }

    @GetMapping(path = "/getUserProducts")
    public @ResponseBody
    List<Product> getUserProducts() {
        final AppUser user = User.getCurrentLoggedInUser();
        return user == null ? new ArrayList<>() : productService.getUserProducts(user.getUserId());
    }
}
