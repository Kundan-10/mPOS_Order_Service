package com.mpos.service;

import com.mpos.exception.ProductNotFoundException;
import com.mpos.exception.UsernameNotFoundException;
import com.mpos.models.Product;
import jdk.jshell.spi.ExecutionControl;

import java.util.List;

public interface ProductService {

    Product addProduct (Product product) throws UsernameNotFoundException, ExecutionControl.UserException;

    Product getProductById(Integer id) throws ProductNotFoundException, UsernameNotFoundException, ExecutionControl.UserException;

    List<Product> getAllProduct() throws ProductNotFoundException, UsernameNotFoundException, ExecutionControl.UserException;
}
