package com.mpos.service;

import com.mpos.exception.OrderNotFoundException;
import com.mpos.exception.ProductException;
import com.mpos.models.Order;
import com.mpos.models.OrderItem;

import java.util.List;
import java.util.Map;

public interface OrderService {

     Order createOrder(String customerName, Map<Integer,Integer> items) throws ProductException;

     List<Order> getAllOrders() throws OrderNotFoundException;


}
