package com.example.demo.architecture.order.adapter.in.web.dto;

import com.example.demo.architecture.order.domain.member.Member.MemberId;
import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class OrderResponseDto {

    private final Order.OrderId orderId;

    private final MemberId memberId;

    private final LocalDateTime orderDate;

    private final OrderStatus orderStatus;

    private final List<OrderItemResponseDto> orderItems;

    public OrderResponseDto(Order domain) {
        this.orderId = domain.getId().isEmpty() ? null : domain.getId().get();
        this.memberId = domain.getMember().getId();
        this.orderDate = domain.getOrderDate();
        this.orderStatus = domain.getStatus();
        this.orderItems = domain.getOrderItems().stream()
            .map(orderItem -> new OrderItemResponseDto(orderItem))
            .collect(Collectors.toList());
    }
}
