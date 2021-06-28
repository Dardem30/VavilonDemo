package com.vavilon.demo.da;

import com.vavilon.demo.bo.product.Product;
import com.vavilon.demo.da.extension.ProductRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryExtension {
}
