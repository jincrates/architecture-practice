package com.example.demo.architecture.order.adapter.in.web.dto;

import com.example.demo.architecture.order.domain.order.OrderItem;
import lombok.Getter;

@Getter
public class OrderItemResponseDto {

    private final String itemName;

    private final int orderPrice;

    private final int count;

    public OrderItemResponseDto(OrderItem orderItem) {
        this.itemName = orderItem.getItem().getName();
        this.orderPrice = orderItem.getOrderPrice();
        this.count = orderItem.getCount();
    }
}
