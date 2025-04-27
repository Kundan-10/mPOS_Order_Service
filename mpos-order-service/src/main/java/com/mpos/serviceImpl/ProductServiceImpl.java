package com.mpos.serviceImpl;

import com.mpos.enums.Role;
import com.mpos.exception.ProductNotFoundException;
import com.mpos.exception.UsernameNotFoundException;
import com.mpos.jwtUtils.JwtUtils;
import com.mpos.models.Product;
import com.mpos.models.User;
import com.mpos.repository.ProductRepository;
import com.mpos.repository.UserRepository;
import com.mpos.service.ProductService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;
    private final JwtUtils jwtUtils;

    public ProductServiceImpl(ProductRepository productRepo, UserRepository userRepository, JwtUtils jwtUtils) {
        this.productRepo = productRepo;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Product addProduct(Product product) throws UsernameNotFoundException, ExecutionControl.UserException {
        User user = jwtUtils.getAuthenticatedUser();
        if(user.getRole() != Role.ADMIN){
            throw new RuntimeException("Admin can add the product");
        }
        return productRepo.save(product);

    }

    @Override
    public Product getProductById(Integer id) throws ProductNotFoundException, UsernameNotFoundException, ExecutionControl.UserException {
        User user = jwtUtils.getAuthenticatedUser();
        if(user.getRole() != Role.ADMIN){
            throw new RuntimeException("Admin can add the product");
        }
       return productRepo.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product not found with ID: "+id));

    }

    @Override
    public List<Product> getAllProduct() throws ProductNotFoundException, UsernameNotFoundException, ExecutionControl.UserException {
        User user = jwtUtils.getAuthenticatedUser();
        if(user.getRole() != Role.ADMIN){
            throw new RuntimeException("Admin can add the product");
        }
        return productRepo.findAll();
    }
}
