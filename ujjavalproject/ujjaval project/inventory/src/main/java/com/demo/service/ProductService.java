package com.demo.service;

import java.util.List;

import com.demo.entity.Product;

public interface ProductService {

	List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
