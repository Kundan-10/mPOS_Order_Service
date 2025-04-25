package com.mpos.controller;

import com.mpos.dto.OrderRequest;
import com.mpos.exception.OrderNotFoundException;
import com.mpos.exception.ProductException;
import com.mpos.exception.ProductNotFoundException;
import com.mpos.models.Order;
import com.mpos.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order Management")
public class OrderServiceController {

    private  final OrderService orderService;

    public OrderServiceController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Create a new order")
    @PostMapping
    public ResponseEntity<Order> addProduct(@RequestBody OrderRequest orderRequest) throws ProductException {
        Order order1 = orderService.createOrder(orderRequest.getCustomerName(), orderRequest.getItems());
        return new ResponseEntity<>(order1, HttpStatus.CREATED);
    }

    @Operation(summary = "List all product")
    @GetMapping()
    public ResponseEntity<List<Order>>getListOfProduct() throws ProductNotFoundException, OrderNotFoundException {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
