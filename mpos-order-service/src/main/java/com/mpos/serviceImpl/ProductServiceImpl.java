package com.mpos.serviceImpl;

import com.mpos.exception.ProductNotFoundException;
import com.mpos.models.Product;
import com.mpos.repository.ProductRepository;
import com.mpos.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepo;

    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product getProductById(Integer id) throws ProductNotFoundException {
       return productRepo.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product not found with ID: "+id));

    }

    @Override
    public List<Product> getAllProduct() throws ProductNotFoundException {
        return productRepo.findAll();
    }
}
