package com.mpos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    private String orderId;

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @PositiveOrZero(message = "Total amount must be zero or positive")
    private double totalAmount;

    private LocalDateTime dateTime;

    @NotEmpty(message = "Order must have at least one item")
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;

    public Order(){}
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Order(String orderId, List<OrderItem> items, LocalDateTime dateTime, double totalAmount, String customerName) {
        this.orderId = orderId;
        this.items = items;
        this.dateTime = dateTime;
        this.totalAmount = totalAmount;
        this.customerName = customerName;
    }
}
