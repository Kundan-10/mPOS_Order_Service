package com.mpos.controller;

import com.mpos.exception.ProductNotFoundException;
import com.mpos.exception.UsernameNotFoundException;
import com.mpos.models.Product;
import com.mpos.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Management")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Add a new product")
    @PostMapping
    public ResponseEntity<Product>addProduct(@RequestBody Product product) throws UsernameNotFoundException, ExecutionControl.UserException {
        Product products = productService.addProduct(product);
        return new ResponseEntity<>(products,HttpStatus.CREATED);
    }

    @Operation(summary = "Get a product by id")
    @GetMapping("/{id}")
    public ResponseEntity<Product>getProductById( @PathVariable Integer id) throws ProductNotFoundException, UsernameNotFoundException, ExecutionControl.UserException {
        Product products = productService.getProductById(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(summary = "List all product")
    @GetMapping()
    public ResponseEntity<List<Product>>getListOfProduct() throws ProductNotFoundException, UsernameNotFoundException, ExecutionControl.UserException {
        List<Product> products = productService.getAllProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
