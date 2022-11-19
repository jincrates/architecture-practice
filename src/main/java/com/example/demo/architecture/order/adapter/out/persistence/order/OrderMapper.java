package com.example.demo.architecture.order.adapter.out.persistence.order;

import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.Order.OrderId;
import org.springframework.stereotype.Component;

@Component
class OrderMapper {
    Order mapToDomainEntity(OrderJpaEntity order) {
        return Order.withId(
                new OrderId(order.getId()),
                order.getStatus()
        );
    }

    OrderJpaEntity mapToJpaEntity(Order order) {
        return OrderJpaEntity.builder()
                .id(order.getId().isEmpty() ? null : order.getId().get().getValue())
                //.deliveryJpaEntity(order.getDelivery())
                //.orderItems(order.getOrderItems())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .build();
    }
}
