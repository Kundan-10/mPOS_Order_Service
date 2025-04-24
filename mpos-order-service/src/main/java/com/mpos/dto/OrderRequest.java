package com.mpos.dto;

import lombok.*;

import java.util.Map;

@Data
@NoArgsConstructor
public class OrderRequest {

    private String customerName;
    private Map<Integer, Integer> items;


    public String getCustomerName() {
        return customerName;
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public OrderRequest(String customerName, Map<Integer, Integer> items) {
        this.customerName = customerName;
        this.items = items;
    }
}
