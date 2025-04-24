package com.mpos.service;

import com.mpos.exception.ProductNotFoundException;
import com.mpos.models.Product;

import java.util.List;

public interface ProductService {

    Product addProduct (Product product);

    Product getProductById(Integer id) throws ProductNotFoundException;

    List<Product> getAllProduct()throws ProductNotFoundException;
}
