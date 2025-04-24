package com.mpos.serviceImpl;

import com.mpos.exception.OrderNotFoundException;
import com.mpos.exception.ProductException;
import com.mpos.models.Order;
import com.mpos.models.OrderItem;
import com.mpos.models.Product;
import com.mpos.repository.OrderRepository;
import com.mpos.repository.ProductRepository;
import com.mpos.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;

    public OrderServiceImpl(OrderRepository orderRepo, ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    @Override
    @Transactional
    public Order createOrder(String customerName, Map<Integer, Integer> items) throws ProductException {

        // Fetch all products by their IDs
        List<Product> products = productRepo.findAllById(items.keySet());

        if (products.size() != items.size()) {
            throw new ProductException("Some products not found.");
        }

        double totalAmount = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (Product product : products) {
            Integer qty = items.get(product.getId());

            if (qty == null) {
                throw new ProductException("Missing quantity for product: " + product.getName());
            }

            if (product.getStock() < qty) {
                throw new ProductException("Insufficient stock for product: " + product.getName());
            }

            product.setStock(product.getStock() - qty);

            // Create OrderItem with product reference
            OrderItem item = new OrderItem();
            item.setProduct(product);  // ✅ Important
            item.setQuantity(qty);
            item.setPrice(product.getPrice()); // per unit price

            orderItems.add(item);

            totalAmount += product.getPrice() * qty;
        }

        productRepo.saveAll(products);

        Order order = new Order();
        order.setOrderId("ORD-" + UUID.randomUUID().toString().substring(0, 8));
        order.setCustomerName(customerName);
        order.setDateTime(LocalDateTime.now());
        order.setTotalAmount(totalAmount);
        order.setItems(orderItems);

        // Set reverse relationship
        for (OrderItem item : orderItems) {
            item.setOrder(order);
        }

        return orderRepo.save(order);
    }

    @Override
    public List<Order> getAllOrders() throws OrderNotFoundException {
        List<Order> orders = orderRepo.findAll();
         if(orders.isEmpty()){
             throw new OrderNotFoundException("No orders Found.");
         }
         return orders;
    }
}
